package com.studio.meowtoon.animesign.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.util.StopWatch;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
public class AspectUtils {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    private static final Log LOG = LogFactory.getLog(
        AspectUtils.class
    );
    
    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    public static Object getExecutedMsec(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        // get the target name.
        Signature signature = pjp.getSignature();
        
        // do the process.
        Object object = pjp.proceed();
        
        stopWatch.stop();
        LOG.info(
            signature.getDeclaringTypeName() + "." + 
            signature.getName() + "(): completed in " + 
            stopWatch.getTotalTimeMillis() + " msec."
        );
        
        // must return the object.
        return object;
    }
    
}