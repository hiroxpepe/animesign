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

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author h.adachi
 */
@Slf4j
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
        Point _init = getDefaultPoint();
        return new Point(
            _init.getX() + (rect.getWidth() / 2),
            _init.getY() + (rect.getHeight() / 2)
        );
    }

    // 矩形の初期位置取得
    public Point getDefaultPoint() {

        // CSSクラス取得
        String _search = getCssClassofStartPosition();

        // 矩形の縦横
        float _width = rect.getWidth();
        float _height = rect.getHeight();

        // CSSクラスにより矩形の初期配置を取得する
        // ※CSSに書いてある値とは連動していない
        // TODO: CSSの自動生成
        if (_search.contains("left-top")) {
            float _x = -(_width / 2);
            float _y = -(_height / 2);
            log.trace("left-top: x:" + _x + ", y:" + _y);
            return new Point(_x, _y);
        } else if (_search.contains("left-center")) {
            float _x = -(_width / 2);
            float _y = (1080 - _height) / 2;
            log.trace("left-center: x:" + _x + ", y:" + _y);
            return new Point(_x, _y);
        } else if (_search.contains("left-bottom")) {
            float _x = -(_width / 2);
            float _y = (1080 - (_height / 2));
            log.trace("left-bottom: x:" + _x + ", y:" + _y);
            return new Point(_x, _y);
        } else if (_search.contains("center-top")) {
            float _x = (1920 - _width) / 2;
            float _y = -(_height / 2);
            log.trace("center-top: x:" + _x + ", y:" + _y);
            return new Point(_x, _y);
        } else if (_search.contains("center-center")) {
            float _x = (1920 - _width) / 2;
            float _y = (1080 - _height) / 2;
            log.trace("center-center: x:" + _x + ", y:" + _y);
            return new Point(_x, _y);
        } else if (_search.contains("center-bottom")) {
            float _x = (1920 - _width) / 2;
            float _y = (1080 - (_height / 2));
            log.trace("center-bottom: x:" + _x + ", y:" + _y);
            return new Point(_x, _y);
        } else if (_search.contains("right-top")) {
            float _x = (1920 - (_width / 2));
            float _y = -(_height / 2);
            log.trace("right-top: x:" + _x + ", y:" + _y);
            return new Point(_x, _y);
        } else if (_search.contains("right-center")) {
            float _x = (1920 - (_width / 2));
            float _y = (1080 - _height) / 2;
            log.trace("right-center: x:" + _x + ", y:" + _y);
            return new Point(_x, _y);
        } else if (_search.contains("right-bottom")) {
            float _x = (1920 - (_width / 2));
            float _y = (1080 - (_height / 2));
            log.trace("right-bottom: x:" + _x + ", y:" + _y);
            return new Point(_x, _y);
        }
        return null;
    }

    ///////////////////////////////////////////////////////////////////////
    // private methods

    private String getCssClassofStartPosition() {
        // " " で分割して以下のような形式のCSSクラスを探す
        // left-top-720px-960px
        String[] _attrClassArray = attrClass.split(" ");
        List<String> _attrClassList = Arrays.asList(_attrClassArray);
        String _search = null;
        for (String _cssClass : _attrClassList) {
            if (_cssClass.contains("left") || _cssClass.contains("right") ||
                _cssClass.contains("top") || _cssClass.contains("bottom") ||
                _cssClass.contains("center")
            ) {
                _search = _cssClass;
                break;
            }
        }
        if (_search == null) {
            log.warn("search is null...");
            return null;
        }
        return _search;
    }
}
