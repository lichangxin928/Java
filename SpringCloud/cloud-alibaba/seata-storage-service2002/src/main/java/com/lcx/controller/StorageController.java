package com.lcx.controller;


import com.lcx.domain.CommonResult;
import com.lcx.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/storage/decrease")
    public CommonResult increaseStorage(Long productId,Integer count){
        storageService.increase(productId,count);
        return new CommonResult(200,"成功");
    }


}
