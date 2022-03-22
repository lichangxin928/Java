package com.lcx.controller;

import com.lcx.domain.CommonResult;
import com.lcx.domain.Order;
import com.lcx.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GlobalTransactional(name = "lcx-create-order",rollbackFor = Exception.class
    )
    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功！");
    }
}
