package com.lcx.workQueue;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class Worker02 {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMqUtils.getChannel();

        System.out.println("worker 2 等待消息");
        channel.basicConsume(QUEUE_NAME,true,(tag,msg)->{
            System.out.println("接收到的消息：" + new String(msg.getBody()));
        },tag ->{
            System.out.println(tag + "消息取消");
        });
    }
}
