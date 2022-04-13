package com.lcx.controller;


import com.lcx.config.ConfirmConfig;
import com.lcx.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RestController
public class SendMessageController {


    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/addMsg/{message}")
    public void sendMsg(@PathVariable("message") String msg){
        System.out.println("当前时间为："+new Date()+"，发送一套信息给两个 TTL 队列：" + msg);
        rabbitTemplate.convertAndSend("X","XA","消息来自 ttl 为 10s 的队列");
        rabbitTemplate.convertAndSend("X","XB","消息来自 ttl 为 4s 的队列");

    }

    // 开始发消息 消息 + ttl
    @GetMapping("/sendTime/{msg}/{time}")
    public void sendMsgOfTime(
            @PathVariable("msg") String msg,
            @PathVariable("time") String ttlTime){
        log.info("当前时间为 {} ，发送一条时长为{}毫秒TTl信息给QC：{}",new Date().toString(),ttlTime,msg);

        rabbitTemplate.convertAndSend("X","XC",msg,message->{
            message.getMessageProperties().setExpiration(ttlTime);
            return message;
        });
    }

    @GetMapping("/sendDelayMsg/{message}/{delayTime}")
    public void sendMsg(
            @PathVariable("message") String message,
            @PathVariable("delayTime") String delayTime
    ){

        log.info("当前时间为：{} 发送一条时长为{}秒信息给延迟队列delayed.queue：{}",new Date(),message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME,
                DelayedQueueConfig.DELAYED_ROUTING_KEY,
                message,msg->{
                    msg.getMessageProperties().setDelay(Integer.valueOf(delayTime));
                    return msg;
                });
    }

    @GetMapping("/sendConfirmMsg/{message}")
    public void sendConfirmMsg(@PathVariable String message){
        CorrelationData correlationData = new CorrelationData("1");
        log.info("发布确认消息：{}",message);
        rabbitTemplate.convertAndSend(ConfirmConfig.EXCHANGE_NAME,
                ConfirmConfig.ROUTING_KEY,
                message,correlationData);
    }
}
