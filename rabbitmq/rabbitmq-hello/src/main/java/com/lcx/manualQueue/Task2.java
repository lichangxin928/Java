package com.lcx.manualQueue;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Task2 {

    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMqUtils.getChannel();
        // 开启发布确认模式 （单个，批量，异步）
        channel.confirmSelect();
        channel.queueDelete(TASK_QUEUE_NAME);
        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String msg = scanner.next();
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出消息：" + msg);
        }

    }
}
