
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
@Table(name="easy_timeline")
public class EasyTimeline implements Serializable {
    
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
    
    @Column(name="duration_to_clear")
    private Long durationToClear;
    
    @Column(name="text_body")
    private String textBody;
        
    @Column(name="translate_grid_point_x")
    private Float translateGridPointX;
    
    @Column(name="translate_grid_point_y")
    private Float translateGridPointY;
    
    @Column(name="translate_duration")
    private Long translateDuration;
    
    @Column(name="rotate_value")
    private Float rotateValue;
    
    @Column(name="scale_mark")
    private String scaleMark;
    
    @Column(name="trim_x_pixel")
    private Long trimXPixel;
    
    @Column(name="trim_y_pixel")
    private Long trimYPixel;
    
    @Column(name="src")
    private String src;
    
    @Column(name="chunk_id") // 塊ID: FK
    private Long chunkId;
    
    @Column(name="is_used")
    private boolean isUsed;
    
}