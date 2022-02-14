package com.lcx.contraller;

import com.lcx.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "payment_global_default_fallback")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 一般请求，没有延时
     * @param id 请求的id
     * @return
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_OK(@PathVariable("id") Integer id){
        return paymentService.payment_OK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand
    public String payment_timeOut(@PathVariable("id") Integer id){
//        int i = 0/1;
        return paymentService.payment_timeOut(id);
    }

    public String payment_global_default_fallback(){
        System.out.println("controller");
        return "global default fallback";
    }

}
