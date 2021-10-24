package com.lcx.bao2;

import org.springframework.stereotype.Component;

// 目标类
@Component("myService")
public class SomeServiceImpl implements SomeService {
    @Override
    public int doSome() {
        System.out.println("do some....");
        return 1;
    }
}
