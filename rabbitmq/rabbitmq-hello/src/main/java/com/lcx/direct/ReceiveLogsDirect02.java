package com.lcx.direct;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class ReceiveLogsDirect02 {

    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args)  throws Exception{

        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        // 生成一个队列
        channel.queueDeclare("disk",false,false,false,null).getQueue();
        // 绑定交换机和队列
        channel.queueBind("disk",EXCHANGE_NAME,"error");
        System.out.println("等待接收消息..");
        channel.basicConsume("disk",true,
                ((consumerTag, message) -> {
                    System.out.println(new String(message.getBody(),"UTF-8"));
                }), consumerTag -> {
                    System.out.println("fail");
                });

    }
}
