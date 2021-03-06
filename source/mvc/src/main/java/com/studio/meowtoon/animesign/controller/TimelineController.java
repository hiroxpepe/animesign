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

import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;

import lombok.val;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studio.meowtoon.animesign.form.ResourceForm;
import com.studio.meowtoon.animesign.service.ResourceService;
import com.studio.meowtoon.animesign.service.ResourceImageService;
import com.studio.meowtoon.animesign.service.WriteTimelineService;
import com.studio.meowtoon.animesign.service.EasyToRawTimelineService;
import com.studio.meowtoon.animesign.response.Response;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@Scope(value="session")
public class TimelineController {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    private static final String RESPONSE_BEAN_ID = "response";

    @NonNull
    private HttpServletRequest request;

    @NonNull
    private ApplicationContext context;

    @NonNull
    private Mapper mapper;

    @NonNull
    private ResourceService resourceService;

    @NonNull
    private WriteTimelineService writeTimelineService;

    @NonNull
    private EasyToRawTimelineService easyToRawTimelineService;

    @NonNull
    private ResourceImageService resourceImageService;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    // 初回リクエスト /index.html ページを返す
    // ※現状では画像ファイルを HTML に埋め込む
    @RequestMapping(
        value = "/index",
        method = RequestMethod.GET
    )
    public void getIndex(
        Model model
    ) {
        log.info("GET request to '/index' from " + request.getRemoteHost());

        // _resource のリスト取得
        val _resourceFormList = new LinkedList<ResourceForm>();
        resourceService.getResourceList().forEach((_resource) -> {
            _resourceFormList.add(mapper.map(_resource, ResourceForm.class));
        });

        // デフォルト背景URL取得
        String _defaultBackground = resourceService.getDefaultBackground();

        // model に対してHTMLで表示するデータをセット
        model.addAttribute("resourceList", _resourceFormList);
        model.addAttribute("defaultBackground", _defaultBackground);
    }

    // Ajax リクエストを受け、タイムライン再生用の JavaScript ファイルを動的生成する
    @RequestMapping(
        value="/build",
        method=RequestMethod.GET,
        headers="Accept=application/json"
    )
    public @ResponseBody Response getBuild() {
        log.info("Ajax GET request to '/build' from " + request.getRemoteHost());

        // timeline データ作成
        buildTimeline();

        // レスポンス用のオブジェクトを返す
        return (Response) context.getBean(
            RESPONSE_BEAN_ID,
            false,
            "write timeline list complete."
        );
    }

    ///////////////////////////////////////////////////////////////////////////
    // private methods

    private void buildTimeline() {
        // _resource 画像の情報取得
        resourceImageService.getResourceInfo();

        // timeline のデータを再構築
        easyToRawTimelineService.convertEasyToRaw();

        // timeline 再生用の JavaSctipt ファイルを出力
        writeTimelineService.createJavaScript();
    }
}
