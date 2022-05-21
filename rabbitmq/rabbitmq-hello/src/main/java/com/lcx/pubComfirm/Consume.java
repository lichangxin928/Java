package com.lcx.pubComfirm;

import com.lcx.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class Consume {
    public static void main(String[] args) throws IOException {

        String []queueNames = new String[]{"12bb8fda-0413-4d72-a42b-85d6391f6557",
                                            "37a08416-cba3-4962-a8d5-da2590988077",
                                            "9f2c7a02-84c6-4a06-82bc-1d6142111326"};

        Channel channel = RabbitMqUtils.getChannel();



    }
}
