package com.studio.meowtoon.animesign.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.studio.meowtoon.animesign.util.AspectUtils;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Aspect
@Component
public class ServiceAspect {
    
    ///////////////////////////////////////////////////////////////////////////
    // public methods
        
    @Around("execution(public * com.studio.meowtoon.animesign.service.*.*(..))")
    public Object aroundForService(ProceedingJoinPoint pjp) throws Throwable {
        Object object = AspectUtils.getExecutedMsec(pjp);
        return object;
    }
    
}