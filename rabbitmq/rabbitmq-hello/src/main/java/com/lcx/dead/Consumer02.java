package com.lcx.dead;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

public class Consumer02 {
    public static final String DEAD_QUEUE = "dead_queue";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        channel.basicConsume(DEAD_QUEUE,true,(tag,msg)->{
            System.out.println("接收到的消息：" + new String(msg.getBody(), StandardCharsets.UTF_8));
        },consumerTag -> {});
    }
}
