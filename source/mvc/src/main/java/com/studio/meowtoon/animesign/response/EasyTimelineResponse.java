package com.studio.meowtoon.animesign.response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Component(value="easyTimelineResponse")
@Scope(value="prototype")
public class EasyTimelineResponse extends Response {
    
    public EasyTimelineResponse(boolean isError, String message) {
        super(isError, message);
    }
    
}