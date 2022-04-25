package com.lcx.consumer;

import com.lcx.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConfirmConsumer {

    @RabbitListener(queues = ConfirmConfig.QUEUE_NAME)
    public void receive(Message message ){
        log.info("消费者接收到了消息：{}",message.getBody());
    }
}
