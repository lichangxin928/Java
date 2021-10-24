package com.lcx.bao3;

import org.springframework.stereotype.Component;

// 目标类

public class SomeServiceImpl implements SomeService {
    @Override
    public int doSome() {
        System.out.println("do some....");
        return 1;
    }


}
