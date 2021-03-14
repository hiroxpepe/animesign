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

package com.studio.meowtoon.animesign.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author h.adachi
 */
@Data
public class EasyTimelineModel implements Serializable {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    private Long id;

    private String targets;

    private Long delay;

    private Long durationToClear;

    private String textBody;

    private Float translateGridPointX;

    private Float translateGridPointY;

    private Long translateDuration;

    private Float rotateValue;

    private String scaleMark;

    private Long trimXPixel;

    private Long trimYPixel;

    private String src;

    private Long chunkId;

    private boolean use;
}
