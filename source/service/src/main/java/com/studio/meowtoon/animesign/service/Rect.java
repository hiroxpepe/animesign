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

import lombok.NonNull;
import lombok.Value;

/**
 * @author h.adachi
 */
@Value
class Rect {

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
