package com.lcx.direct;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class ReceiveLogsDirect01 {

    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args)  throws Exception{

        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        // 生成一个队列
        channel.queueDeclare("console",false,false,false,null).getQueue();
        // 绑定交换机和队列
        channel.queueBind("console",EXCHANGE_NAME,"info");
        channel.queueBind("console",EXCHANGE_NAME,"warning");
        System.out.println("等待接收消息..");
        channel.basicConsume("console",true,
                ((consumerTag, message) -> {
                    System.out.println(new String(message.getBody(),"UTF-8"));
                }), consumerTag -> {
                    System.out.println("fail");
                });

    }

}
