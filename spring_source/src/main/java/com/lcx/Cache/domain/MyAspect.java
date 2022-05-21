package com.lcx.Cache.domain;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Before(value = "execution(public void com.lcx.Cache.domain.A.doSome())")
    public void before(){
        System.out.println("before");
    }
}
