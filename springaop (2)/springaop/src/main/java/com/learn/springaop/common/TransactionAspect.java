package com.learn.springaop.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TransactionAspect {

    @Before("@annotation(com.learn.springaop.annotation.Transaction)")//joinpoint
    public void begainTx(){
        log.info("transaction started.......");
    }


    @AfterReturning("@annotation(com.learn.springaop.annotation.Transaction)")
    public void commitTransaction(){
        log.info("Transaction committed....");
    }

    @AfterThrowing("@annotation(com.learn.springaop.annotation.Transaction)")
     public void rollbackTransaction(){
        log.info("Transaction rollback....");
    }



}
