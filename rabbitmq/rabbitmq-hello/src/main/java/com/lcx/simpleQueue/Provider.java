package com.lcx.simpleQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class Provider {

    // 队列名称
    public static final String QUEUE_NAME = "hello";

    // 发消息
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 工厂 ip 连接 RabbitMQ的队列
        factory.setHost("106.14.242.94");

        // 设置用户名和密码
        factory.setUsername("admin");
        factory.setPassword("123");

        // 创建连接
        Connection connection = factory.newConnection();
        // 获取信道
        Channel channel = connection.createChannel();
        /**
         * 生成一个队列
         * 1. 队列名称
         * 2. 是否需要持久化
         * 3. 该队列是否只供一个消费者消费
         * 4. 是否自动删除
         * 5. 其他参数
         */
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        String message = "hello world";
        /**
         * 1. 发送到哪一个交换机
         * 2. 路由的 key 值是什么
         * 3. 其他参数信息
         * 4. 发送消息的消息体
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("发送完成");
    }
}
