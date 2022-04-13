package com.lcx.direct;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DirectLogs {

    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String msg = scanner.next();
            channel.basicPublish(EXCHANGE_NAME, "info", null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("发出消息: " + msg);
        }
    }
}
