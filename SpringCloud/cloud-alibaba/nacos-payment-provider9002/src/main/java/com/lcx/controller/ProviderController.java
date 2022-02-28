package com.lcx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ProviderController {

    @GetMapping("/payment/nacos/{id}")
    public String getPay(@PathVariable("id") Integer id){
        String result = "接收到的 id 为" + id;

        return result;
    }
}
