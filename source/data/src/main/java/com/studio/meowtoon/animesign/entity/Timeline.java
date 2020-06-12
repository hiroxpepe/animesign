
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
@Table(name="timeline")
public class Timeline implements Serializable {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true)
    private Long id;
    
    @Column(name="targets")
    private String targets;
    
    @Column(name="`offset`")
    private Long offset;
    
    @Column(name="opacity_value")
    private Long opacityValue;
    
    @Column(name="opacity_duration")
    private Long opacityDuration;
    
    @Column(name="translate_x_value")
    private Long translateXValue;
    
    @Column(name="translate_x_duration")
    private Long translateXDuration;
    
    @Column(name="translate_y_value")
    private Long translateYValue;
    
    @Column(name="translate_y_duration")
    private Long translateYDuration;
    
    @Column(name="src")
    private String src;
    
    @Column(name="duration")
    private Long duration;
    
    @Column(name="easing")
    private String easing;
    
    @Column(name="rotate_value")
    private Float rotateValue;
    
    @Column(name="rotate_duration")
    private Long rotateDuration;
    
    @Column(name="rotate_easing")
    private String rotateEasing;
    
    @Column(name="scale_value")
    private Float scaleValue;
    
    @Column(name="scale_duration")
    private Long scaleDuration;
    
    @Column(name="scale_easing")
    private String scaleEasing;
    
    @Column(name="text_body")
    private String textBody;
    
    @Column(name="text_x")
    private Long textX;
    
    @Column(name="text_y")
    private Long textY;
    
    @Column(name="kind")
    private String kind;
    
    @Column(name="is_used")
    private boolean isUsed;
    
}