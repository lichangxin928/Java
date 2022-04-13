package com.lcx.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayedQueueConfig {

    // 队列
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    // 交换机
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    // routingKey
    public static final String DELAYED_ROUTING_KEY = "delayed.routingKey";


    @Bean
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE_NAME);
    }
    // 声明交换机
    @Bean
    public CustomExchange delayedExchange(){
        /**
         * 1. 交换机的名称
         * 2. 交换机的类型
         * 3. 是否需要持久化
         * 4. 是否需要自动删除
         * 5. 其他的参数
         */

        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type","direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME,"x-delayed-message",
                true,false,args);
    }

    @Bean
    public Binding delayedQueueBindingExchange(@Qualifier("delayedExchange")CustomExchange exchange,
                                               @Qualifier("delayedQueue")Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(DELAYED_ROUTING_KEY).noargs();
    }

}
