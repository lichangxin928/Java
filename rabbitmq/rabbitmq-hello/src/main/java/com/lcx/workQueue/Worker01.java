package com.lcx.workQueue;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * 消费者
 */
public class Worker01 {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMqUtils.getChannel();

        System.out.println("worker 1 等待消息");
        channel.basicConsume(QUEUE_NAME,false,(tag,msg)->{
            System.out.println("接收到的消息：" + new String(msg.getBody()));
            channel.basicAck(msg.getEnvelope().getDeliveryTag(),false);
        },tag ->{
            System.out.println(tag + "消息取消");
        });
    }
}
