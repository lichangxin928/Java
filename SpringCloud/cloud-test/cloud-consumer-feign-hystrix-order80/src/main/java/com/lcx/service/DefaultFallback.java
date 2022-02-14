package com.lcx.service;

import org.springframework.stereotype.Component;

@Component
public class DefaultFallback implements PaymentService{
    @Override
    public String payment_OK(Integer id) {
        System.out.println("111");
        return "服务器异常";
    }

    @Override
    public String payment_timeOut(Integer id) {
        System.out.println("222");
        return "服务器异常";
    }
}
