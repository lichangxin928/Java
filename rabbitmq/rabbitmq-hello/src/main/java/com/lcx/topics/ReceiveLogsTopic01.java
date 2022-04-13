package com.lcx.topics;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class ReceiveLogsTopic01 {

    public static final String EXCHANGE_NAME = "topic_name";

    public static void main(String[] args) throws Exception{

        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        channel.queueDeclare("q1",false,false,false,null);
        channel.queueBind("q1",EXCHANGE_NAME,"*.orange.*");
        System.out.println("等待接收消息..");
        channel.basicConsume("q1",true,(consumerTag, message) -> {
            System.out.println("接收到的消息：" + new String(message.getBody(),"UTF-8"));
            System.out.println("绑定键：" + message.getEnvelope().getRoutingKey());
        },consumerTag -> {});
    }
}
