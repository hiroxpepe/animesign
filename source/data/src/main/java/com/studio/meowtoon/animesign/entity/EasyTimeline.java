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

package com.studio.meowtoon.animesign.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author h.adachi
 */
@Data
@Entity
@Table(name="easy_timelines")
public class EasyTimeline implements Serializable {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true)
    Long id;

    @Column(name="targets")
    String targets;

    @Column(name="delay")
    Long delay;

    @Column(name="duration_to_clear")
    Long durationToClear;

    @Column(name="text_body")
    String textBody;

    @Column(name="translate_grid_point_x")
    Float translateGridPointX;

    @Column(name="translate_grid_point_y")
    Float translateGridPointY;

    @Column(name="translate_duration")
    Long translateDuration;

    @Column(name="rotate_value")
    Float rotateValue;

    @Column(name="scale_mark")
    String scaleMark;

    @Column(name="trim_x_pixel")
    Long trimXPixel;

    @Column(name="trim_y_pixel")
    Long trimYPixel;

    @Column(name="src")
    String src;

    @Column(name="use")
    boolean use;

}