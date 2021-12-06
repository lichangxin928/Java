package com.lcx.controller;

import com.lcx.domain.Payment;
import com.lcx.domain.ReturnResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    public RestTemplate restTemplate;

    @PostMapping("/create")
    public ReturnResult<Payment> create( Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/Pay",payment,ReturnResult.class);
    }

    @GetMapping("/get/{id}")
    public ReturnResult<Payment> getById(@PathVariable("id") Integer id){
        System.out.println(id);
        return restTemplate.getForObject(PAYMENT_URL + "/Pay/" + id,ReturnResult.class );

    }
}
