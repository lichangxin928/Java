package com.lcx.service.impl;

import com.lcx.dao.OrderDao;
import com.lcx.domain.Order;
import com.lcx.service.AccountService;
import com.lcx.service.OrderService;
import com.lcx.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;


    @Override
    public void create(Order order) {
        System.out.println("开始新建订单------");
        orderDao.create(order);
        System.out.println("订单创建完成");

        System.out.println("订单微服务开始调用库存做扣减");
        storageService.decrease(order.getProductId(), order.getCount());
        System.out.println("扣减结束------");

        System.out.println("微服务开始调用账号，做扣减");
        accountService.decrease(order.getUserId(),order.getMoney());
        System.out.println("扣减结束-----");

        System.out.println("修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        System.out.println("订单修改完成");




    }
}
