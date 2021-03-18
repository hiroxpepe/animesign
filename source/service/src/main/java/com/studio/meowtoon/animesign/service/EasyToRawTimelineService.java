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

import java.util.List;

import lombok.val;
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
@RequiredArgsConstructor
@Service
public class EasyToRawTimelineService {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    @NonNull
    private ApplicationContext context;

    @NonNull
    private EasyTimelineRepository easyTimelineRepository;

    @NonNull
    private TimelineRepository timelineRepository;

    @NonNull
    private ResourceRepository resourceRepository;

    ///////////////////////////////////////////////////////////////////////////
    // public Method

    // _easyTimeline のデータ処理して timeline に挿入する。
    @Transactional
    public void convertEasyToRaw() {
        try {
            // _easyTimeline のデータのクリアの情報の部分を計算してレコードに追加する
            // 全体で Offset が順番通りに並んでないと動かない

            // timeline データ全削除
            timelineRepository.deleteAll();

            // _easyTimeline データ取得
            List<EasyTimeline> _easyTimelineList = easyTimelineRepository.findAll();

            // timeline データ作成
            createTimeline(_easyTimelineList);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // private Method

    private long getTotalDuration(List<EasyTimeline> easyTimelineList) {
        try {
            // 全体の尺取得
            long _totalDuration = 0L;
            for (EasyTimeline _easyTimeline : easyTimelineList) {
                if (_easyTimeline.getDurationToClear() != null) {
                    long _duration = _easyTimeline.getDelay()+ _easyTimeline.getDurationToClear();
                    if (_duration > _totalDuration) {
                        _totalDuration = _duration;
                    }
                }
            }
            return _totalDuration;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
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
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private void createTimeline(List<EasyTimeline> easyTimelineList) {
        try {
            // 全体の尺取得
            long _totalDuration = getTotalDuration(easyTimelineList);
            for (EasyTimeline _easyTimeline : easyTimelineList) {
                log.debug(_easyTimeline.toString());
                ///////////////////////////////////////////////////////////////
                // 表示処理
                Timeline _timeline1 = context.getBean(Timeline.class);
                _timeline1.setTargets(_easyTimeline.getTargets());
                _timeline1.setDelay(_easyTimeline.getDelay());
                _timeline1.setOpacityValue(1L);
                _timeline1.setOpacityDuration(500L);
                _timeline1.setTextBody(_easyTimeline.getTextBody());
                ///////////////////////////////////////////////////////////////
                // 位置移動の設定
                // "background" 以外の場合
                // ※ "title" は移動や回転、拡大縮小したいので固定しない
                if (!_easyTimeline.getTargets().contains("background")) {
                    Resource _resource = resourceRepository.findByAttrId(_easyTimeline.getTargets().replace("#", ""));
                    _timeline1.setTranslateXValue((long) convertGridPointToPixelValue(_easyTimeline, _resource).getX());
                    _timeline1.setTranslateYValue((long) convertGridPointToPixelValue(_easyTimeline, _resource).getY());
                    _timeline1.setTranslateXDuration(_easyTimeline.getTranslateDuration());
                    _timeline1.setTranslateYDuration(_easyTimeline.getTranslateDuration());
                }
                ///////////////////////////////////////////////////////////////
                // 回転の設定
                if (_easyTimeline.getRotateValue() != null) {
                    _timeline1.setRotateValue(_easyTimeline.getRotateValue());
                    _timeline1.setRotateDuration(_easyTimeline.getTranslateDuration());
                    _timeline1.setRotateEasing("easeInOutQuint");
                }
                ///////////////////////////////////////////////////////////////
                // 拡大縮小の設定
                if (_easyTimeline.getScaleMark() != null) {
                    String _scaleMark = _easyTimeline.getScaleMark();
                    if (_scaleMark.contains("+")) {
                        if (_scaleMark.equals("+")) {
                            _timeline1.setScaleValue(1.1F);
                        }
                        if (_scaleMark.equals("++")) {
                            _timeline1.setScaleValue(1.5F);
                        }
                        if (_scaleMark.equals("+++")) {
                            _timeline1.setScaleValue(2.0F);
                        }
                        _timeline1.setScaleDuration(getComplementedDurationToClear(_easyTimeline, _totalDuration));
                        _timeline1.setScaleEasing("linear");
                    }
                    if (_scaleMark.contains("-")) {
                        if (_scaleMark.equals("-")) {
                            _timeline1.setScaleValue(0.9F);
                        }
                        if (_scaleMark.equals("--")) {
                            _timeline1.setScaleValue(0.75F);
                        }
                        if (_scaleMark.equals("---")) {
                            _timeline1.setScaleValue(0.5F);
                        }
                        _timeline1.setScaleDuration(getComplementedDurationToClear(_easyTimeline, _totalDuration));
                        _timeline1.setScaleEasing("linear");
                    }
                }
                ///////////////////////////////////////////////////////////////
                // その他の設定
                // "background" でも "scroll" でもない場合
                if (!_easyTimeline.getTargets().contains("background") && !_easyTimeline.getTargets().contains("scroll")) {
                    _timeline1.setEasing("easeInOutQuint");
                    _timeline1.setKind("show-object");
                }
                // "scroll" の場合
                else if (_easyTimeline.getTargets().contains("scroll")) {
                    _timeline1.setEasing("linear");
                    _timeline1.setKind("show-scroll");
                // "background" の場合
                } else if (_easyTimeline.getTargets().contains("background")) {
                    _timeline1.setDuration(0L);
                    _timeline1.setEasing("linear");
                    _timeline1.setKind("show-background");
                }
                ///////////////////////////////////////////////////////////////
                // 画像リソースを変更することが出来る TODO: オブジェクトでは？
                _timeline1.setSrc(_easyTimeline.getSrc());
                _timeline1.setUse(_easyTimeline.isUse());
                timelineRepository.save(_timeline1);

                ///////////////////////////////////////////////////////////////
                // 消去処理
                if (_easyTimeline.getDurationToClear() != null) {
                    Timeline _timeline2 = context.getBean(Timeline.class);
                    Resource _resource = resourceRepository.findByAttrId(_easyTimeline.getTargets().replace("#", ""));
                    _timeline2.setTargets(_easyTimeline.getTargets());
                    _timeline2.setDelay(_easyTimeline.getDelay() +
                        getComplementedDurationToClear(_easyTimeline, _totalDuration)
                    );
                    _timeline2.setOpacityValue(0L);
                    _timeline2.setOpacityDuration(500L);
                    _timeline2.setEasing("linear");
                    _timeline2.setKind("hide-object");
                    _timeline2.setUse(_easyTimeline.isUse());
                    timelineRepository.save(_timeline2);
                    // 消してから初期位置に移動
                    Timeline _timeline3 = context.getBean(Timeline.class);
                    _timeline3.setTargets(_easyTimeline.getTargets());
                    _timeline3.setDelay(_easyTimeline.getDelay() +
                        getComplementedDurationToClear(_easyTimeline, _totalDuration) + 1000 // 1秒後
                    );
                    _timeline3.setTranslateXValue((long) getDefaultPositionValue(_resource).getX());
                    _timeline3.setTranslateYValue((long) getDefaultPositionValue(_resource).getY());
                    _timeline3.setTranslateXDuration(0L);
                    _timeline3.setTranslateYDuration(0L);
                    _timeline3.setEasing("linear");
                    _timeline3.setKind("init-object");
                    _timeline3.setUse(_easyTimeline.isUse());
                    timelineRepository.save(_timeline3);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    // 元々配置してある位置を取得
    private Point getDefaultPositionValue(Resource resource) {

        // 対象リソースの矩形縦横
        val _rect = new Rect(
            resource.getAttrSrcWidth(),
            resource.getAttrSrcHeight()
        );

        // CSS クラスの文字列
        String _attrClass = resource.getAttrClass();

        // 矩形の初期位置取得
        val _pointHelper = new PointHelper(_attrClass, _rect);
        return _pointHelper.getDefaultPoint();
    }

    // 元々配置してある位置からグリッドポイントに移動するには上下左右を計算で出す
    private Point convertGridPointToPixelValue(EasyTimeline easyTimeline, Resource resource) {

        // 対象リソースの矩形縦横
        val _rect = new Rect(
            resource.getAttrSrcWidth(),
            resource.getAttrSrcHeight()
        );

        // CSS クラスの文字列
        String _attrClass = resource.getAttrClass();

        ///////////////////////////////////////////////////////////////////////
        // グリッドのある地点からある地点に移動する _rect を計算する

        // 矩形の初期位置の中心ポイント取得
        val _pointHelper = new PointHelper(_attrClass, _rect);
        Point _centerOfStartPoint = _pointHelper.getCenterPoint();
        log.trace("centerOfStartPoint: " + _centerOfStartPoint.toString());

        // 矩形の移動後の中心ポイント取得
        val _centerOfEndPoint = new Point(
            easyTimeline.getTranslateGridPointX() * 120,
            easyTimeline.getTranslateGridPointY() * 120
        );
        log.trace("centerOfEndPoint: " + _centerOfEndPoint.toString());

        // 矩形を移動する為の値取得
        Point _pointToMove = getVectorOfMove(_centerOfStartPoint, _centerOfEndPoint);
        log.trace("pointToMove: " + _pointToMove.toString());

        // 移動する値を返す
        float _translateValueX = _pointToMove.getX();
        float _translateValueY = _pointToMove.getY();
        return new Point(
            _translateValueX,
            _translateValueY
        );
    }

    private Point getVectorOfMove(Point centerPointOfStart, Point centerPointOfEnd) {
        // X軸
        float _moveX;
        float _startX = centerPointOfStart.getX();
        float _endX = centerPointOfEnd.getX();
        _moveX = _endX - _startX;

        // Y軸
        float _moveY;
        float _startY = centerPointOfStart.getY();
        float _endY = centerPointOfEnd.getY();
        _moveY = _endY - _startY;

        // 移動する値を返す
        return new Point(
            _moveX,
            _moveY
        );
    }
}
