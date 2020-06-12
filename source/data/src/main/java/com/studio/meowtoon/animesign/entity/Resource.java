
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
@Table(name="resource")
public class Resource implements Serializable {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true)
    private Long id;
    
    @Column(name="elem")
    private String elem;
    
    @Column(name="attr_id")
    private String attrId;
    
    @Column(name="attr_class")
    private String attrClass;
    
    @Column(name="attr_src")
    private String attrSrc;
    
    @Column(name="attr_src_width")
    private Long attrSrcWidth;
    
    @Column(name="attr_src_height")
    private Long attrSrcHeight;
    
    @Column(name="options")
    private String options;
    
    @Column(name="is_used")
    private boolean isUsed;
    
}