package com.example.logger;

import java.util.Arrays;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* com.example.services.*.*(..))")
    public Object log(ProceedingJoinPoint jp) throws Throwable{
        
        String methodName = jp.getSignature().getName();
        Object [] arguments = jp.getArgs();

        logger.info("Method name " + methodName + " with parameters " + Arrays.asList(arguments));
    
        Object returnValByMethod = jp.proceed();

        logger.info("Returned value by method " + returnValByMethod);

        return returnValByMethod;
    }
}