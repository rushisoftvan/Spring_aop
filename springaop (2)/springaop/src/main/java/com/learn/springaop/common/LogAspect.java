package com.learn.springaop.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogAspect {


    @Before("@annotation(com.learn.springaop.annotation.Log)") //
    public void startMethod(JoinPoint joinPoint){ //jointpoint give the method name  which method use annotation log
      if(log.isDebugEnabled()){
          log.debug("<<<<<<<<< {}",joinPoint.getSignature());
      }
    }

    @AfterReturning(("@annotation(com.learn.springaop.annotation.Log)"))
    public void endMethod(JoinPoint joinPoint){
        if(log.isDebugEnabled()){
            log.debug(">>>>>>>>>{}",joinPoint.getSignature());
        }
    }
}
