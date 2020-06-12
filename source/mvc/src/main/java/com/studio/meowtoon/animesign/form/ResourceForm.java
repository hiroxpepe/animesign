
package com.studio.meowtoon.animesign.form;

import java.io.Serializable;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Data
@Component(value="resourceForm")
@Scope(value="prototype")
public class ResourceForm implements Serializable {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    private Long id;
    
    private String elem;
    
    private String attrId;
    
    private String attrClass;
    
    private String attrSrc;
    
    private String options;
    
}