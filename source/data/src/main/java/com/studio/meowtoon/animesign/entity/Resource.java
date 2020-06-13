
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
@Table(name="resources")
public class Resource implements Serializable {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true)
    Long id;
    
    @Column(name="elem")
    String elem;
    
    @Column(name="attr_id")
    String attrId;
    
    @Column(name="attr_class")
    String attrClass;
    
    @Column(name="attr_src")
    String attrSrc;
    
    @Column(name="attr_src_width")
    Long attrSrcWidth;
    
    @Column(name="attr_src_height")
    Long attrSrcHeight;
    
    @Column(name="options")
    String options;
    
    @Column(name="is_used")
    boolean isUsed;
    
}