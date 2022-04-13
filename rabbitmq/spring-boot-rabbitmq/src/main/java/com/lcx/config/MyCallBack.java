package com.lcx.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback ,RabbitTemplate.ReturnsCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        String id = correlationData != null ? correlationData.getId() : "";

        if(ack){
            log.info("交换机已经收到ID 为：{}的消息",id);
        }else {
            log.info("交换机没有收到id为{} 的消息",id);
        }
    }


    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.error("消息 {} 被交换机 {} 退回了，退回原因：{} 路由key：{}");
    }
}
