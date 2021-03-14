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

package com.studio.meowtoon.animesign.response;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.studio.meowtoon.animesign.model.HogeModel;

/**
 * @author h.adachi
 */
@Getter
@Component(value="hogeResponse")
@Scope(value="prototype")
public class HogeResponse extends Response {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    @NonNull
    private final List<HogeModel> hogeModelList;

    ///////////////////////////////////////////////////////////////////////////
    // Constructor

    public HogeResponse(List<HogeModel> hogeModelList, boolean isError, String message) {
        super(isError, message);
        this.hogeModelList = hogeModelList;
    }
}
