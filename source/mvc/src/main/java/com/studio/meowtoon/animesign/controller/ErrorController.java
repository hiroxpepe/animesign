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

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studio.meowtoon.animesign.response.Response;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@Scope(value="session")
public class ErrorController {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    private static final String RESPONSE_BEAN_ID = "response";

    @NonNull
    private ApplicationContext context;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    // エラーハンドラー
    @ExceptionHandler
    @ResponseBody
    public Response handleException(
        Exception e
    ) {
        log.error(e.getMessage());
        return (Response) context.getBean(
            RESPONSE_BEAN_ID,
            true,
            e.getMessage()
        );
    }

}