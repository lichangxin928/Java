package com.lcx.Cache.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("a")
public class A {

    @Autowired
    private B b;

    public void doSome(){
        System.out.println("do some...");
    }
}
