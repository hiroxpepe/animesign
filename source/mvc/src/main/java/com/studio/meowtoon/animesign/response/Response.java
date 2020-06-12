
package com.studio.meowtoon.animesign.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Component(value="response")
@Scope(value="prototype")
public class Response {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    @NonNull
    private boolean isError;
    
    @NonNull
    private String message;
    
}