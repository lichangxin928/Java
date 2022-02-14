package com.lcx.service;

import com.lcx.domain.Payment;
import com.lcx.domain.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICE")
public interface FeignService {

    @GetMapping("/Pay/{id}")
    ReturnResult<Payment> ById(@PathVariable("id") Integer id);
}
