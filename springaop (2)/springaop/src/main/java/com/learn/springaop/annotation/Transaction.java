package com.learn.springaop.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD    })
@Retention(RetentionPolicy.RUNTIME)
public @interface Transaction {

}
