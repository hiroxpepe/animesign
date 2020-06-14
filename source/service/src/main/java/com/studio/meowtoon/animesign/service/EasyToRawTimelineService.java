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

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

import lombok.Value;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studio.meowtoon.animesign.entity.EasyTimeline;
import com.studio.meowtoon.animesign.entity.Timeline;
import com.studio.meowtoon.animesign.entity.Resource;
import com.studio.meowtoon.animesign.repository.EasyTimelineRepository;
import com.studio.meowtoon.animesign.repository.TimelineRepository;
import com.studio.meowtoon.animesign.repository.ResourceRepository;

/**
 * @author h.adachi
 */
@Slf4j
@Service
public class EasyToRawTimelineService {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    @Inject
    private ApplicationContext context;

    @Inject
    private EasyTimelineRepository easyTimelineRepository;

    @Inject
    private TimelineRepository timelineRepository;

    @Inject
    private ResourceRepository resourceRepository;

    ///////////////////////////////////////////////////////////////////////////
    // public Method

    // easyTimeline のデータ処理して timeline に挿入する。
    @Transactional
    public void convertEasyToRaw() {
        try {
            // easyTimeline のデータのクリアの情報の部分を計算してレコードに追加する
            // 全体で Offset が順番通りに並んでないと動かない

            // timeline データ全削除
            timelineRepository.deleteAll();

            // easyTimeline データ取得
            List<EasyTimeline> easyTimelineList = easyTimelineRepository.findAll();

            // timeline データ作成
            createTimeline(easyTimelineList);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // private Method

    private long getTotalDuration(List<EasyTimeline> easyTimelineList) {
        try {
            // 全体の尺取得
            long totalDuration = 0L;
            for (EasyTimeline easyTimeline : easyTimelineList) {
                if (easyTimeline.getDurationToClear() != null) {
                    long duration = easyTimeline.getDelay()+ easyTimeline.getDurationToClear();
                    if (duration > totalDuration) {
                        totalDuration = duration;
                    }
                }
            }
            return totalDuration;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private long getComplementedDurationToClear(EasyTimeline easyTimeline, long totalDuration) {
        try {
            // durationToClear が -1 の場合は全体の尺までの長さを計算して返す
            if (easyTimeline.getDurationToClear() == -1L) {
                return totalDuration - easyTimeline.getDelay();
            } else {
                return easyTimeline.getDurationToClear();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void createTimeline(List<EasyTimeline> easyTimelineList) {
        try {
            // 全体の尺取得
            long totalDuration = getTotalDuration(easyTimelineList);

            for (EasyTimeline easyTimeline : easyTimelineList) {
                log.debug(easyTimeline.toString());
                ///////////////////////////////////////////////////////////////
                // 表示処理
                Timeline timeline1 = context.getBean(Timeline.class);
                timeline1.setTargets(easyTimeline.getTargets());
                timeline1.setDelay(easyTimeline.getDelay());
                timeline1.setOpacityValue(1L);
                timeline1.setOpacityDuration(500L);
                timeline1.setTextBody(easyTimeline.getTextBody());
                ///////////////////////////////////////////////////////////////
                // 位置移動の設定
                // "background" 以外の場合
                // ※ "title" は移動や回転、拡大縮小したいので固定しない
                if (!easyTimeline.getTargets().contains("background")) {
                    Resource resource = resourceRepository.findByAttrId(easyTimeline.getTargets().replace("#", ""));
                    timeline1.setTranslateXValue((long) convertGridPointToPixelValue(easyTimeline, resource).getX());
                    timeline1.setTranslateYValue((long) convertGridPointToPixelValue(easyTimeline, resource).getY());
                    timeline1.setTranslateXDuration(easyTimeline.getTranslateDuration());
                    timeline1.setTranslateYDuration(easyTimeline.getTranslateDuration());
                }
                ///////////////////////////////////////////////////////////////
                // 回転の設定
                if (easyTimeline.getRotateValue() != null) {
                    timeline1.setRotateValue(easyTimeline.getRotateValue());
                    timeline1.setRotateDuration(easyTimeline.getTranslateDuration());
                    timeline1.setRotateEasing("easeInOutQuint");
                }
                ///////////////////////////////////////////////////////////////
                // 拡大縮小の設定
                if (easyTimeline.getScaleMark() != null) {
                    String scaleMark = easyTimeline.getScaleMark();
                    if (scaleMark.contains("+")) {
                        if (scaleMark.equals("+")) {
                            timeline1.setScaleValue(1.1F);
                        }
                        if (scaleMark.equals("++")) {
                            timeline1.setScaleValue(1.5F);
                        }
                        if (scaleMark.equals("+++")) {
                            timeline1.setScaleValue(2.0F);
                        }
                        timeline1.setScaleDuration(getComplementedDurationToClear(easyTimeline, totalDuration));
                        timeline1.setScaleEasing("linear");
                    }
                    if (scaleMark.contains("-")) {
                        if (scaleMark.equals("-")) {
                            timeline1.setScaleValue(0.9F);
                        }
                        if (scaleMark.equals("--")) {
                            timeline1.setScaleValue(0.75F);
                        }
                        if (scaleMark.equals("---")) {
                            timeline1.setScaleValue(0.5F);
                        }
                        timeline1.setScaleDuration(getComplementedDurationToClear(easyTimeline, totalDuration));
                        timeline1.setScaleEasing("linear");
                    }
                }
                ///////////////////////////////////////////////////////////////
                // その他の設定
                // "background" でも "scroll" でもない場合
                if (!easyTimeline.getTargets().contains("background") && !easyTimeline.getTargets().contains("scroll")) {
                    timeline1.setEasing("easeInOutQuint");
                    timeline1.setKind("show-object");
                }
                // "scroll" の場合
                else if (easyTimeline.getTargets().contains("scroll")) {
                    timeline1.setEasing("linear");
                    timeline1.setKind("show-scroll");
                // "background" の場合
                } else if (easyTimeline.getTargets().contains("background")) {
                    timeline1.setDuration(0L);
                    timeline1.setEasing("linear");
                    timeline1.setKind("show-background");
                }
                ///////////////////////////////////////////////////////////////
                // 画像リソースを変更することが出来る TODO: オブジェクトでは？
                timeline1.setSrc(easyTimeline.getSrc());
                timeline1.setUsed(easyTimeline.isUsed());
                timelineRepository.save(timeline1);

                ///////////////////////////////////////////////////////////////
                // 消去処理
                if (easyTimeline.getDurationToClear() != null) {
                    Timeline timeline2 = context.getBean(Timeline.class);
                    Resource resource = resourceRepository.findByAttrId(easyTimeline.getTargets().replace("#", ""));
                    timeline2.setTargets(easyTimeline.getTargets());
                    timeline2.setDelay(
                        easyTimeline.getDelay() +
                        getComplementedDurationToClear(easyTimeline, totalDuration)
                    );
                    timeline2.setOpacityValue(0L);
                    timeline2.setOpacityDuration(500L);
                    timeline2.setEasing("linear");
                    timeline2.setKind("hide-object");
                    timeline2.setUsed(easyTimeline.isUsed());
                    timelineRepository.save(timeline2);
                    // 消してから初期位置に移動
                    Timeline timeline3 = context.getBean(Timeline.class);
                    timeline3.setTargets(easyTimeline.getTargets());
                    timeline3.setDelay(
                        easyTimeline.getDelay() +
                        getComplementedDurationToClear(easyTimeline, totalDuration) + 1000 // 1秒後
                    );
                    timeline3.setTranslateXValue((long) getDefaultPositionValue(resource).getX());
                    timeline3.setTranslateYValue((long) getDefaultPositionValue(resource).getY());
                    timeline3.setTranslateXDuration(0L);
                    timeline3.setTranslateYDuration(0L);
                    timeline3.setEasing("linear");
                    timeline3.setKind("init-object");
                    timeline3.setUsed(easyTimeline.isUsed());
                    timelineRepository.save(timeline3);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    // 元々配置してある位置を取得
    private Point getDefaultPositionValue(Resource resource) {

        // 対象リソースの矩形縦横
        Rect rect = new Rect(
            resource.getAttrSrcWidth(),
            resource.getAttrSrcHeight()
        );

        // CSS クラスの文字列
        String attrClass = resource.getAttrClass();

        // 矩形の初期位置取得
        PointHelper pointHelper = new PointHelper(attrClass, rect);
        return pointHelper.getDefaultPoint();
    }

    // 元々配置してある位置からグリッドポイントに移動するには上下左右を計算で出す
    private Point convertGridPointToPixelValue(EasyTimeline easyTimeline, Resource resource) {

        // 対象リソースの矩形縦横
        Rect rect = new Rect(
            resource.getAttrSrcWidth(),
            resource.getAttrSrcHeight()
        );

        // CSS クラスの文字列
        String attrClass = resource.getAttrClass();

        ///////////////////////////////////////////////////////////////////////
        // グリッドのある地点からある地点に移動する rect を計算する

        // 矩形の初期位置の中心ポイント取得
        PointHelper pointHelper = new PointHelper(attrClass, rect);
        Point centerOfStartPoint = pointHelper.getCenterPoint();
        log.trace("centerOfStartPoint: " + centerOfStartPoint.toString());

        // 矩形の移動後の中心ポイント取得
        Point centerOfEndPoint = new Point(
            Float.valueOf(easyTimeline.getTranslateGridPointX()) * 120,
            Float.valueOf(easyTimeline.getTranslateGridPointY()) * 120
        );
        log.trace("centerOfEndPoint: " + centerOfEndPoint.toString());

        // 矩形を移動する為の値取得
        Point pointToMove = getVectorOfMove(centerOfStartPoint, centerOfEndPoint);
        log.trace("pointToMove: " + pointToMove.toString());

        // 移動する値を返す
        float translateValueX = pointToMove.getX();
        float translateValueY = pointToMove.getY();
        return new Point(
            translateValueX,
            translateValueY
        );
    }

    private Point getVectorOfMove(Point centerPointOfStart, Point centerPointOfEnd) {
        // X軸
        float moveX;
        float startX = centerPointOfStart.getX();
        float endX = centerPointOfEnd.getX();
        moveX = endX - startX;

        // Y軸
        float moveY;
        float startY = centerPointOfStart.getY();
        float endY = centerPointOfEnd.getY();
        moveY = endY - startY;

        // 移動する値を返す
        return new Point(
            moveX,
            moveY
        );
    }

    @RequiredArgsConstructor
    public class PointHelper {

        ///////////////////////////////////////////////////////////////////////
        // Field

        @NonNull
        String attrClass;

        @NonNull
        Rect rect;

        ///////////////////////////////////////////////////////////////////////
        // public methods

        // 矩形の中心位置取得
        public Point getCenterPoint() {
            // 初期位置に自分の矩形の 幅/2、高さ/2 を足す
            Point init = getDefaultPoint();
            return new Point(
                init.getX() + (rect.getWidth() / 2),
                init.getY() + (rect.getHeight() / 2)
            );
        }

        // 矩形の初期位置取得
        public Point getDefaultPoint() {

            // CSSクラス取得
            String search = getCssClassofStartPosition();

            // 矩形の縦横
            float width = rect.getWidth();
            float height = rect.getHeight();

            // CSSクラスにより矩形の初期配置を取得する
            // ※CSSに書いてある値とは連動していない
            // TODO: CSSの自動生成
            if (search.contains("left-top")) {
                float x = -(width / 2);
                float y = -(height / 2);
                log.trace("left-top: x:" + x + ", y:" + y);
                return new Point(x, y);
            } else if (search.contains("left-center")) {
                float x = -(width / 2);
                float y = (1080 - height) / 2;
                log.trace("left-center: x:" + x + ", y:" + y);
                return new Point(x, y);
            } else if (search.contains("left-bottom")) {
                float x = -(width / 2);
                float y = (1080 - (height / 2));
                log.trace("left-bottom: x:" + x + ", y:" + y);
                return new Point(x, y);
            } else if (search.contains("center-top")) {
                float x = (1920 - width) / 2;
                float y = -(height / 2);
                log.trace("center-top: x:" + x + ", y:" + y);
                return new Point(x, y);
            } else if (search.contains("center-center")) {
                float x = (1920 - width) / 2;
                float y = (1080 - height) / 2;
                log.trace("center-center: x:" + x + ", y:" + y);
                return new Point(x, y);
            } else if (search.contains("center-bottom")) {
                float x = (1920 - width) / 2;
                float y = (1080 - (height / 2));
                log.trace("center-bottom: x:" + x + ", y:" + y);
                return new Point(x, y);
            } else if (search.contains("right-top")) {
                float x = (1920 - (width / 2));
                float y = -(height / 2);
                log.trace("right-top: x:" + x + ", y:" + y);
                return new Point(x, y);
            } else if (search.contains("right-center")) {
                float x = (1920 - (width / 2));
                float y = (1080 - height) / 2;
                log.trace("right-center: x:" + x + ", y:" + y);
                return new Point(x, y);
            } else if (search.contains("right-bottom")) {
                float x = (1920 - (width / 2));
                float y = (1080 - (height / 2));
                log.trace("right-bottom: x:" + x + ", y:" + y);
                return new Point(x, y);
            }
            return null;
        }

        ///////////////////////////////////////////////////////////////////////
        // private methods

        private String getCssClassofStartPosition() {
            // " " で分割して以下のような形式のCSSクラスを探す
            // left-top-720px-960px
            String[] attrClassArray = attrClass.split(" ");
            List<String> attrClassList = Arrays.asList(attrClassArray);
            String search = null;
            for (String cssClass : attrClassList) {
                if (cssClass.contains("left") || cssClass.contains("right") ||
                    cssClass.contains("top") || cssClass.contains("bottom") ||
                    cssClass.contains("center")
                ) {
                    search = cssClass;
                    break;
                }
            }
            if (search == null) {
                log.warn("search is null...");
                return null;
            }
            return search;
        }
    }

    @Value
    public class Point {

        ///////////////////////////////////////////////////////////////////////
        // Field

        @NonNull
        private float x;

        @NonNull
        private float y;

        ///////////////////////////////////////////////////////////////////////
        // public methods

        public float getGridX() {
            return x / 120;
        }

        public float getGridY() {
            return y / 120;
        }

    }

    @Value
    public class Rect {

        ///////////////////////////////////////////////////////////////////////
        // Field

        @NonNull
        private float width;

        @NonNull
        private float height;

        ///////////////////////////////////////////////////////////////////////
        // public methods

        public float getGridWidth() {
            return width / 120;
        }

        public float getGridHeight() {
            return height / 120;
        }
    }

}