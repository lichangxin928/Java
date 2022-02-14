package com.lcx.controller;

import com.lcx.domain.Payment;
import com.lcx.domain.ReturnResult;
import com.lcx.service.PaymentService;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PaymentController {

    @Resource
    private PaymentService service;


    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/Pay/{id}")
    public ReturnResult getById(@PathVariable("id") Integer id){
        System.out.println(id);
        Payment pay = service.getById(id);
        return new ReturnResult(200, "查询成功8001", pay);
    }

    @GetMapping("/Pay")
    public ReturnResult getAllStudent(){

        List<Payment> all = service.findAll();
        ReturnResult returnResult = null;
        if (all == null){
            return new ReturnResult(400,"参数传递有问题",null);
        }
        else if(all.size() != 0){
            returnResult = new ReturnResult(200,"查询成功",all);
            return returnResult;
        }else {
            return new ReturnResult(200,"没有这数据",null);
        }

    }

    @PostMapping("/Pay")
    public ReturnResult addStu(@RequestBody Payment payment){

        ReturnResult returnResult = null;
        if (payment.getId() == null || payment.getSerial() ==null){
            return new ReturnResult(400,"传入参数有问题",null);
        }else{
            service.insertPayment(payment);
            return new ReturnResult(200,"插入成功！" + serverPort,null);
        }
    }
}

