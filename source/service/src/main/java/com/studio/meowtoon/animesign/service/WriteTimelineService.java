/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.studio.meowtoon.animesign.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studio.meowtoon.animesign.entity.Timeline;
import com.studio.meowtoon.animesign.repository.TimelineRepository;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class WriteTimelineService {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    @NonNull
    private TimelineRepository repository;

    ///////////////////////////////////////////////////////////////////////////
    // public Method

    // _timeline 再生用の javascript ファイルを動的に
    // 生成してファイル出力する。
    @Transactional
    public void createJavaScript() {
        try {
            List<Timeline> _timelienList = repository.findByUseOrderByDelayAsc(true);
            if (_timelienList == null) {
                log.warn("timelien list is empty.");
                return;
            }

            // JavaScript の関数を動的に作成する
            String _javaScript = addTimeline(_timelienList);
            _javaScript += initTimeline(_timelienList);

            // ファイル出力
            writeJavaScript(_javaScript);

        } catch (IOException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // private Method

    private String initTimeline(List<Timeline> timelienList) {
        String _javaScript = "const initTimeline = () => {\n";
        // _timeline リストをループ処理する
        for (Timeline _timeline : timelienList) {
            if (_timeline.getTargets().contains("balloon")) {
                _javaScript += "\t$('" + _timeline.getTargets().replace("img", "text") + "').html('')\n";
            }
        } // ループ終了
        _javaScript += "}\n";
        return _javaScript;
    }

    // JavaScript の関数を動的に作成する
    private String addTimeline(List<Timeline> timelienList) {
        String _javaScript = "const buildTimeline = (basicTimeline) => {\n";
        _javaScript += "\t" + "basicTimeline.add({\n";

        ///////////////////////////////////////////////////////////////////
        // タイムラインデータの登録: _timeline リストをループ処理する
        for (Timeline _timeline : timelienList) {
            ///////////////////////////////////////////////////////////////////
            // 背景画像変更パターン
            if (_timeline.getTargets().contains("background")) {
                _javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + _timeline.getTargets() + "',\n" +
                    "\t\t" + "offset: " + _timeline.getDelay() + ",\n" +
                    "\t\t" + "src: '" + _timeline.getSrc() + "',\n" +
                    "\t\t" + "duration: " + _timeline.getDuration() + ",\n" +
                    "\t\t" + "easing: '" + _timeline.getEasing() + "'\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // キャラ表示パターン
            if (!_timeline.getTargets().contains("balloon") && _timeline.getKind().contains("show")) {
                _javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + _timeline.getTargets() + "',\n" +
                    "\t\t" + "offset: " + _timeline.getDelay() + ",\n" +
                    "\t\t" + "opacity: {value: " + _timeline.getOpacityValue() + ", duration: " + _timeline.getOpacityDuration() + "},\n" +
                    "\t\t" + "translateX: {value: " + _timeline.getTranslateXValue() + ", duration: " + _timeline.getTranslateXDuration() + "},\n" +
                    "\t\t" + "translateY: {value: " + _timeline.getTranslateYValue() + ", duration: " + _timeline.getTranslateYDuration() + "},\n" +
                    getRotate(_timeline) +
                    getScale(_timeline) +
                    "\t\t" + "easing: '" + _timeline.getEasing() + "'\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // キャラ消去パターン
            if (!_timeline.getTargets().contains("balloon") && _timeline.getKind().contains("hide")) {
                _javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + _timeline.getTargets() + "',\n" +
                    "\t\t" + "offset: " + _timeline.getDelay() + ",\n" +
                    "\t\t" + "opacity: {value: " + _timeline.getOpacityValue() + ", duration: " + _timeline.getOpacityDuration() + "},\n" +
                    "\t\t" + "easing: '" + _timeline.getEasing() + "'\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // キャラ初期位置移動パターン
            if (!_timeline.getTargets().contains("balloon") && _timeline.getKind().contains("init")) {
                _javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + _timeline.getTargets() + "',\n" +
                    "\t\t" + "offset: " + _timeline.getDelay() + ",\n" +
                    "\t\t" + "translateX: {value: " + _timeline.getTranslateXValue() + ", duration: " + _timeline.getTranslateXDuration() + "},\n" +
                    "\t\t" + "translateY: {value: " + _timeline.getTranslateYValue() + ", duration: " + _timeline.getTranslateYDuration() + "},\n" +
                    "\t\t" + "easing: '" + _timeline.getEasing() + "'\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // 吹き出し表示パターン
            if (_timeline.getTextBody() != null && _timeline.getKind().contains("show")) {
                _javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + _timeline.getTargets() + ", " + _timeline.getTargets().replace("img", "text") + "',\n" +
                    "\t\t" + "offset: " + _timeline.getDelay() + ",\n" +
                    "\t\t" + "opacity: {value: " + _timeline.getOpacityValue() + ", duration: " + _timeline.getOpacityDuration() + "},\n" +
                    "\t\t" + "translateX: {value: " + _timeline.getTranslateXValue() + ", duration: " + _timeline.getTranslateXDuration() + "},\n" +
                    "\t\t" + "translateY: {value: " + _timeline.getTranslateYValue() + ", duration: " + _timeline.getTranslateYDuration() + "},\n" +
                    "\t\t" + "easing: '" + _timeline.getEasing() + "',\n" +
                    "\t\t" + "complete: (anime) => {\n" +
                        "\t\t\t" + "showBalloonText('" + _timeline.getTextBody() + "',\n" +
                        "\t\t\t'" + _timeline.getTargets() + "',\n" +
                        "\t\t\t'" + _timeline.getTargets().replace("img", "text") + "',\n" +
                        "\t\t\t" + _timeline.getTextX() + "," + _timeline.getTextY()  + ");\n" +
                    "\t\t" + "}\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // 吹き出し消去パターン
            if (_timeline.getTargets().contains("balloon") && _timeline.getKind().contains("hide")) {
                _javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + _timeline.getTargets() + ", " + _timeline.getTargets().replace("img", "text") + "',\n" +
                    "\t\t" + "offset: " + _timeline.getDelay() + ",\n" +
                    "\t\t" + "opacity: {value: " + _timeline.getOpacityValue() + ", duration: " + _timeline.getOpacityDuration() + "},\n" +
                    "\t\t" + "easing: '" + _timeline.getEasing() + "',\n" +
                    "\t\t" + "complete: (anime) => {\n" +
                        "\t\t\t" + "hideBalloonText('" + _timeline.getTargets().replace("img", "text") + "');\n" +
                    "\t\t" + "}\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // 吹き出し初期位置移動パターン
            if (_timeline.getTargets().contains("balloon") && _timeline.getKind().contains("init")) {
                _javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + _timeline.getTargets() + ", " + _timeline.getTargets().replace("img", "text") + "',\n" +
                    "\t\t" + "offset: " + _timeline.getDelay() + ",\n" +
                    "\t\t" + "translateX: {value: " + _timeline.getTranslateXValue() + ", duration: " + _timeline.getTranslateXDuration() + "},\n" +
                    "\t\t" + "translateY: {value: " + _timeline.getTranslateYValue() + ", duration: " + _timeline.getTranslateYDuration() + "},\n" +
                    "\t\t" + "easing: '" + _timeline.getEasing() + "'\n";
                continue;
            }
        } // ループ終了
        _javaScript += "\t" + "});\n";
        _javaScript += "}\n";
        return _javaScript;
    }

    // 回転の設定
    private String getRotate(Timeline timeline) {
        if (timeline.getRotateValue() != null) {
            return "\t\t" + "rotate: {value: " + timeline.getRotateValue() + ", duration: " + timeline.getRotateDuration() + ", easing: '" + timeline.getRotateEasing() + "'},\n";
        }
        return "";
    }

    // 拡大縮小の設定
    private String getScale(Timeline timeline) {
        if (timeline.getScaleValue() != null) {
            return "\t\t" + "scale: {value: " + timeline.getScaleValue() + ", duration: " + timeline.getScaleDuration() + ", easing: '" + timeline.getScaleEasing() + "'},\n";
        }
        return "";
    }

    // /var/www/html/animesign-resources/ にファイル書き出し
    private void writeJavaScript(String animeTimeline) throws IOException {
        FileUtils.writeStringToFile(
            new File("/var/www/html/animesign/resources/scripts/animesign.timeline.js"), // FIXME: どこから読むか？
            animeTimeline,
            "UTF-8"
        );
    }
}
