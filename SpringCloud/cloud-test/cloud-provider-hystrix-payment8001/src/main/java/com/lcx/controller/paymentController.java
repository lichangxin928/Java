package com.lcx.controller;

import com.lcx.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class paymentController {

    @Resource
    private PaymentService payment;

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_OK(@PathVariable("id") Integer id){
        System.out.println("ok!");
        String result = payment.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_timeOut(@PathVariable("id") Integer id){
        System.out.println("timeout request");
        String result = payment.paymentInfo_timeOut(id);
        return result;
    }

    @GetMapping("/payment/circuit/{id}")
    public String payment_circuitBreak(@PathVariable("id") Integer id){

        return payment.paymentCircuitBreaker(id);
    }
}
