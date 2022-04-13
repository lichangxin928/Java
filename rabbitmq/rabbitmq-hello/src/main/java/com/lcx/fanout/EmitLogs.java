package com.lcx.fanout;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EmitLogs {

    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String msg = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("发出消息: " + msg);
        }
    }
}
