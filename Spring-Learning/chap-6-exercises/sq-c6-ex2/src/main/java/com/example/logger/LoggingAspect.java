package com.example.logger;

import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* com.example.services.*.*(..))")
    public Object log(ProceedingJoinPoint jp) throws Throwable{
        logger.info("Method will execute");

        Object result = jp.proceed();  // proceed to the original method
    
        logger.info("Method executed");
        
        return result;
    }
}
