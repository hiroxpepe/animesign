/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.studio.meowtoon.animesign.util;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.util.StopWatch;

/**
 * @author h.adachi
 */
@Slf4j
public class AspectUtils {

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    public static Object getExecutedMsec(ProceedingJoinPoint pjp) throws Throwable {
        val _stopWatch = new StopWatch();
        _stopWatch.start();

        // get the target name.
        Signature _signature = pjp.getSignature();

        // do the process.
        Object _object = pjp.proceed();

        _stopWatch.stop();
        log.info(_signature.getDeclaringTypeName() + "." +
            _signature.getName() + "(): completed in " +
            _stopWatch.getTotalTimeMillis() + " msec."
        );

        // must return the _object.
        return _object;
    }
}
