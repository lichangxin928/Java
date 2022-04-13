package com.lcx.topics;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TopicLogs {

    public static final String EXCHANGE_NAME = "topic_name";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String msg = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"lazy.orange.312",null,msg.getBytes(StandardCharsets.UTF_8));
        }
    }
}
