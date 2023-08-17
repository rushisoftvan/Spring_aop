package com.learn.springaop.common;

import com.learn.springaop.annotation.AroundExample;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogAspect {


    @Before("@annotation(com.learn.springaop.annotation.Log)") //
    public void startMethod(JoinPoint joinPoint) { //jointpoint give the method name  which method use annotation log
        if (log.isDebugEnabled()) {
            log.debug("<<<<<<<<< {}", joinPoint.getSignature());
        }
    }

    @AfterReturning(("@annotation(com.learn.springaop.annotation.Log)"))
    public void endMethod(JoinPoint joinPoint) {
        if (log.isDebugEnabled()) {
            log.debug(">>>>>>>>>{}", joinPoint.getSignature());
        }
    }

    @Around(("@annotation(com.learn.springaop.annotation.AroundExample)"))
    public void m1(ProceedingJoinPoint joinPoint) throws Throwable {

       try{
           log.info("method started {} ",joinPoint.getSignature().getName());
           joinPoint.proceed();
           log.info("method ended {}",joinPoint.getSignature().getName());
       }
       catch(Exception e){
           log.info(" roleback  {}",joinPoint.getSignature().getName());
           e.printStackTrace();

       }
    }
}
