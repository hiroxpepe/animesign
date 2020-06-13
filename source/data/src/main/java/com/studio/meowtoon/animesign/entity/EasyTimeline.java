
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

    @Column(name="is_used")
    boolean isUsed;

}