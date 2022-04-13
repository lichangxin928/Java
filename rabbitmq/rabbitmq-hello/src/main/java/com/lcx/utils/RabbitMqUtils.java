package com.lcx.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtils {
    public static Channel getChannel(){
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 工厂 ip 连接 RabbitMQ的队列
        factory.setHost("106.14.242.94");

        // 设置用户名和密码
        factory.setUsername("admin");
        factory.setPassword("123");

        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
