
package com.studio.meowtoon.animesign.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Data
@Entity
@Table(name="timelines")
public class Timeline implements Serializable {

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

    @Column(name="opacity_value")
    Long opacityValue;

    @Column(name="opacity_duration")
    Long opacityDuration;

    @Column(name="translate_x_value")
    Long translateXValue;

    @Column(name="translate_x_duration")
    Long translateXDuration;

    @Column(name="translate_y_value")
    Long translateYValue;

    @Column(name="translate_y_duration")
    Long translateYDuration;

    @Column(name="src")
    String src;

    @Column(name="duration")
    Long duration;

    @Column(name="easing")
    String easing;

    @Column(name="rotate_value")
    Float rotateValue;

    @Column(name="rotate_duration")
    Long rotateDuration;

    @Column(name="rotate_easing")
    String rotateEasing;

    @Column(name="scale_value")
    Float scaleValue;

    @Column(name="scale_duration")
    Long scaleDuration;

    @Column(name="scale_easing")
    String scaleEasing;

    @Column(name="text_body")
    String textBody;

    @Column(name="text_x")
    Long textX;

    @Column(name="text_y")
    Long textY;

    @Column(name="kind")
    String kind;

    @Column(name="is_used")
    boolean isUsed;

}