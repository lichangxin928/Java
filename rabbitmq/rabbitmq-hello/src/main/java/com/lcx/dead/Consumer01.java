package com.lcx.dead;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class Consumer01 {

    // 普通交换机
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    // 死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";
    // 普通队列名称
    public static final String NORMAL_QUEUE = "normal_queue";
    // 死信队列的名称
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);

        Map<String,Object> map = new HashMap();
        // map.put("x-message-ttl",10000);
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key","lisi");
        map.put("x-max-length",5);

        channel.queueDeclare(NORMAL_QUEUE,false,false,false,map);
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);

        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"zhangsan");
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");


        channel.basicConsume(NORMAL_QUEUE,true,((consumerTag, message) -> {
            System.out.println("接收到了消息 " + new String(message.getBody(), StandardCharsets.UTF_8));
        }),consumerTag -> {});
    }

}
