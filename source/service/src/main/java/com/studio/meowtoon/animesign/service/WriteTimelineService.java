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
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studio.meowtoon.animesign.entity.Timeline;
import com.studio.meowtoon.animesign.repository.TimelineRepository;

/**
 * @author h.adachi
 */
@Slf4j
@Service
public class WriteTimelineService {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    @Inject
    private ApplicationContext context;

    @Inject
    private TimelineRepository repository;

    ///////////////////////////////////////////////////////////////////////////
    // public Method

    // timeline 再生用の javascript ファイルを動的に
    // 生成してファイル出力する。
    @Transactional
    public void createJavaScript() {
        try {
            List<Timeline> timelienList = repository.findByUseOrderByDelayAsc(true);
            if (timelienList == null) {
                log.warn("timelien list is empty.");
                return;
            }

            // JavaScript の関数を動的に作成する
            String javaScript = addTimeline(timelienList);
            javaScript += initTimeline(timelienList);

            // ファイル出力
            writeJavaScript(javaScript);

        } catch (IOException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // private Method

    private String initTimeline(List<Timeline> timelienList) {
        String javaScript = "function initTimeline() {\n";
        // timeline リストをループ処理する
        for (Timeline timeline : timelienList) {
            if (timeline.getTargets().contains("balloon")) {
                javaScript += "\t$('" + timeline.getTargets().replace("img", "text") + "').html('')\n";
            }
        } // ループ終了
        javaScript += "}\n";
        return javaScript;
    }

    // JavaScript の関数を動的に作成する
    private String addTimeline(List<Timeline> timelienList) {
        String javaScript = "function buildTimeline(basicTimeline) {\n";
        javaScript += "\t" + "basicTimeline.add({\n";

        ///////////////////////////////////////////////////////////////////
        // タイムラインデータの登録: timeline リストをループ処理する
        for (Timeline timeline : timelienList) {
            ///////////////////////////////////////////////////////////////////
            // 背景画像変更パターン
            if (timeline.getTargets().contains("background")) {
                javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + timeline.getTargets() + "',\n" +
                    "\t\t" + "offset: " + timeline.getDelay() + ",\n" +
                    "\t\t" + "src: '" + timeline.getSrc() + "',\n" +
                    "\t\t" + "duration: " + timeline.getDuration() + ",\n" +
                    "\t\t" + "easing: '" + timeline.getEasing() + "'\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // キャラ表示パターン
            if (!timeline.getTargets().contains("balloon") && timeline.getKind().contains("show")) {
                javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + timeline.getTargets() + "',\n" +
                    "\t\t" + "offset: " + timeline.getDelay() + ",\n" +
                    "\t\t" + "opacity: {value: " + timeline.getOpacityValue() + ", duration: " + timeline.getOpacityDuration() + "},\n" +
                    "\t\t" + "translateX: {value: " + timeline.getTranslateXValue() + ", duration: " + timeline.getTranslateXDuration() + "},\n" +
                    "\t\t" + "translateY: {value: " + timeline.getTranslateYValue() + ", duration: " + timeline.getTranslateYDuration() + "},\n" +
                    getRotate(timeline) +
                    getScale(timeline) +
                    "\t\t" + "easing: '" + timeline.getEasing() + "'\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // キャラ消去パターン
            if (!timeline.getTargets().contains("balloon") && timeline.getKind().contains("hide")) {
                javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + timeline.getTargets() + "',\n" +
                    "\t\t" + "offset: " + timeline.getDelay() + ",\n" +
                    "\t\t" + "opacity: {value: " + timeline.getOpacityValue() + ", duration: " + timeline.getOpacityDuration() + "},\n" +
                    "\t\t" + "easing: '" + timeline.getEasing() + "'\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // キャラ初期位置移動パターン
            if (!timeline.getTargets().contains("balloon") && timeline.getKind().contains("init")) {
                javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + timeline.getTargets() + "',\n" +
                    "\t\t" + "offset: " + timeline.getDelay() + ",\n" +
                    "\t\t" + "translateX: {value: " + timeline.getTranslateXValue() + ", duration: " + timeline.getTranslateXDuration() + "},\n" +
                    "\t\t" + "translateY: {value: " + timeline.getTranslateYValue() + ", duration: " + timeline.getTranslateYDuration() + "},\n" +
                    "\t\t" + "easing: '" + timeline.getEasing() + "'\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // 吹き出し表示パターン
            if (timeline.getTextBody() != null && timeline.getKind().contains("show")) {
                javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + timeline.getTargets() + ", " + timeline.getTargets().replace("img", "text") + "',\n" +
                    "\t\t" + "offset: " + timeline.getDelay() + ",\n" +
                    "\t\t" + "opacity: {value: " + timeline.getOpacityValue() + ", duration: " + timeline.getOpacityDuration() + "},\n" +
                    "\t\t" + "translateX: {value: " + timeline.getTranslateXValue() + ", duration: " + timeline.getTranslateXDuration() + "},\n" +
                    "\t\t" + "translateY: {value: " + timeline.getTranslateYValue() + ", duration: " + timeline.getTranslateYDuration() + "},\n" +
                    "\t\t" + "easing: '" + timeline.getEasing() + "',\n" +
                    "\t\t" + "complete: function(anim) {\n" +
                        "\t\t\t" + "showBalloonText('" + timeline.getTextBody() + "',\n" +
                        "\t\t\t'" + timeline.getTargets() + "',\n" +
                        "\t\t\t'" + timeline.getTargets().replace("img", "text") + "',\n" +
                        "\t\t\t" + timeline.getTextX() + "," + timeline.getTextY()  + ");\n" +
                    "\t\t" + "}\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // 吹き出し消去パターン
            if (timeline.getTargets().contains("balloon") && timeline.getKind().contains("hide")) {
                javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + timeline.getTargets() + ", " + timeline.getTargets().replace("img", "text") + "',\n" +
                    "\t\t" + "offset: " + timeline.getDelay() + ",\n" +
                    "\t\t" + "opacity: {value: " + timeline.getOpacityValue() + ", duration: " + timeline.getOpacityDuration() + "},\n" +
                    "\t\t" + "easing: '" + timeline.getEasing() + "',\n" +
                    "\t\t" + "complete: function(anim) {\n" +
                        "\t\t\t" + "hideBalloonText('" + timeline.getTargets().replace("img", "text") + "');\n" +
                    "\t\t" + "}\n";
                continue;
            }
            ///////////////////////////////////////////////////////////////////
            // 吹き出し初期位置移動パターン
            if (timeline.getTargets().contains("balloon") && timeline.getKind().contains("init")) {
                javaScript +=
                    "\t" + "}).add({\n" +
                    "\t\t" + "targets: '" + timeline.getTargets() + ", " + timeline.getTargets().replace("img", "text") + "',\n" +
                    "\t\t" + "offset: " + timeline.getDelay() + ",\n" +
                    "\t\t" + "translateX: {value: " + timeline.getTranslateXValue() + ", duration: " + timeline.getTranslateXDuration() + "},\n" +
                    "\t\t" + "translateY: {value: " + timeline.getTranslateYValue() + ", duration: " + timeline.getTranslateYDuration() + "},\n" +
                    "\t\t" + "easing: '" + timeline.getEasing() + "'\n";
                continue;
            }
        } // ループ終了
        javaScript += "\t" + "});\n";
        javaScript += "}\n";
        return javaScript;
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
            new File("../../var/www/html/animesign/resources/scripts/animesign.timeline.js"), // 【重要】FIXME: どこから読むか？
            animeTimeline,
            "UTF-8"
        );
    }

}