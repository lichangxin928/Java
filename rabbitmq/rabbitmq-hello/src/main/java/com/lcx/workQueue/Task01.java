package com.lcx.workQueue;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;;

import java.io.IOException;
import java.util.Scanner;

public class Task01 {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMqUtils.getChannel();
        // channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("发送消息完成：" + message );
        }
    }
}
