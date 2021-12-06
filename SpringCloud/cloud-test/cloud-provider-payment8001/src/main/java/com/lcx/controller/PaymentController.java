package com.lcx.controller;

import com.lcx.domain.Payment;
import com.lcx.domain.ReturnResult;
import com.lcx.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PaymentController {

    @Resource
    private PaymentService service;

    @GetMapping("/Pay/{id}")
    public ReturnResult getById(@PathVariable("id") Integer id){
        System.out.println(id);
        Payment pay = service.getById(id);
        return new ReturnResult(200, "查询成功", pay);
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
            return new ReturnResult(200,"插入成功！",null);
        }
    }
}

//@RestController
//public class PaymentController {
//    @Resource
//    private PaymentService paymentService;
//
//    //只传给前端CommonResult，不需要前端了解其他的组件
//    @PostMapping(value = "/payment/create")
//    public ReturnResult create(@RequestBody Payment payment){
//        int result = paymentService.insertPayment(payment);
//        if(result > 0){
//            return new ReturnResult(200,"插入数据成功",result);
//        }else{
//            return new ReturnResult(444,"插入数据失败",null);
//        }
//    }
//    @GetMapping(value = "/payment/get/{id}")
//    public ReturnResult getPaymentById(@PathVariable("id") Integer id){
//        Payment payment = paymentService.getById(id);
//        if(payment != null){
//            return new ReturnResult(200,"查询成功",payment);
//        }else{
//            return new ReturnResult(444,"没有对应记录,查询ID："+id,null);
//        }
//    }
//}
