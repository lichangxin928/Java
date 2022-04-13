package com.lcx.manualQueue;

import com.lcx.utils.RabbitMqUtils;
import com.lcx.utils.SleepUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class Worker01 {

    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("c1 等待");
        channel.basicQos(2);
        // 手动应答
        channel.basicConsume(TASK_QUEUE_NAME,false,
                (tag,msg)->{
                    SleepUtils.sleep(1L);
                    System.out.println("接收到消息:" + new String(msg.getBody(), "UTF-8"));
                    channel.basicAck(msg.getEnvelope().getDeliveryTag(),false);
        },
                tag->{
                    System.out.println("消息接收失败");

                });
    }
}
