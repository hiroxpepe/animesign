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

package com.studio.meowtoon.animesign.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@Scope(value="session")
public class EditController {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    @NonNull
    private HttpServletRequest request;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    @RequestMapping(
        value = "/edit/chunk",
        method = RequestMethod.GET
    )
    public void getEditChunk(
        Model model
    ) {
        log.info("GET request to '/edit/chunk' from " + request.getRemoteHost());
    }

    // chunk を呼び出すボタン
    //  => chunk_id のプルダウンで検索(選択)出来るUI　※名前を付けられる

    // chunk id ごとの easytimeline リストを表示する
    //  => リストへの行の追加・更新・削除、上下移動
    //  => chunk で紐付けられた easytimeline を名前をつけてパターンかエピソードに
    //     保存出来る

    // ある chunk の一部分をデリートORカットする機能
    // ある chunk と別の chunk をマージする機能
    // ある chunk の一部分に別の chunk をインサートORペーストする機能
    // ある chunk の選択部分のオフセットを一括してシフトする機能
    // ある chunk の選択部分のパラメータの値を一括して増減する機能
    // ある chunk の選択部分のパラメータの一部を置き換える機能

    ///////////////////////////////////////////////////////////////////////////
    // private methods

}