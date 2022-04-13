package com.lcx.dead;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

public class Produce {
    // 普通交换机
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

//        AMQP.BasicProperties properties =
//                new AMQP.BasicProperties()
//                        .builder().expiration("2000").build();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        for (int i = 0; i < 10; i++) {
            String msg = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",null,msg.getBytes(StandardCharsets.UTF_8));

        }

    }
}
