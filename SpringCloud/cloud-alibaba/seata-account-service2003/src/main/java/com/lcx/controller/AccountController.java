package com.lcx.controller;


import com.lcx.domain.CommonResult;
import com.lcx.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/account/decrease")
    public CommonResult increase(Long userId, BigDecimal count){
        try{
            accountService.decrease(userId,count);
        }catch (Exception e){
            return new CommonResult(444,"fail");
        }

        return new CommonResult(200,"成功！！");
    }
}
