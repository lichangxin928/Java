package com.lcx.simpleQueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static final String QUEUE_NAME = "hello";

    public  static DeliverCallback deliverCallback = (tag,msg) ->{
        System.out.println(msg.getBody());
    };

    public static CancelCallback cancelCallback = tag->{
        System.out.println("消息消费被中断");
    };

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 工厂 ip 连接 RabbitMQ的队列
        factory.setHost("106.14.242.94");

        // 设置用户名和密码
        factory.setUsername("admin");
        factory.setPassword("123");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /**
         * 消费消息
         * 1. 消费哪个队列
         * 2. 消费成功之后是否自动答复
         * 3. 消费者成功的回调
         * 4. 消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
