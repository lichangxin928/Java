package com.lcx.fanout;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

public class ReceiveLogs01 {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args)  throws Exception{

        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        // 生成一个队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定交换机和队列
        channel.queueBind(queue,EXCHANGE_NAME,"");
        System.out.println("等待接收消息..");
        channel.basicConsume(queue,true,
                ((consumerTag, message) -> {
                    System.out.println(new String(message.getBody(),"UTF-8"));
                }), consumerTag -> {
                    System.out.println("fail");
                });
    }
}
