
package com.studio.meowtoon.animesign.model;

import java.io.Serializable;

import lombok.Data;

///////////////////////////////////////////////////////////////////////////////
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
    
    private boolean isUsed;
    
}