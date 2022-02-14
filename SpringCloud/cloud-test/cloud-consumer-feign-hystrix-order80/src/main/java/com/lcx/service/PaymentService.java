package com.lcx.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = DefaultFallback.class)
public interface PaymentService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_timeOut(@PathVariable("id") Integer id);

}
