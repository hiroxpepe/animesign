package com.studio.meowtoon.animesign.controller;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studio.meowtoon.animesign.response.Response;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Slf4j
@Controller
@Scope(value="session")
public class ErrorController {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    private static final String RESPONSE_BEAN_ID = "response";
        
    @Inject
    private ApplicationContext context = null;
    
    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    // エラーハンドラー
    @ExceptionHandler
    @ResponseBody
    public Response handleException(
        Exception e
    ) {
        log.error(e.getMessage());
        return (Response) context.getBean(
            RESPONSE_BEAN_ID,
            true,
            e.getMessage()
        );
    }
    
}