package com.lcx.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TtlQueueConfig {

    private static final String NORMAL_EXCHANGE = "X";
    private static final String DEAD_EXCHANGE = "Y";

    private static final String QUEUE_A = "QA";
    private static final String QUEUE_B = "QB";
    private static final String DEAD_LETTER_QUEUE = "QD";
    private static final String QUEUE_C = "QC";

    // 声明 QC
    @Bean("queueC")
    public Queue queueC(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key","YD");

        return QueueBuilder.durable(QUEUE_C).withArguments(map).build();
    }

    @Bean
    public DirectExchange xExchange(){
        return new DirectExchange(NORMAL_EXCHANGE);
    }

    @Bean
    public DirectExchange yExchange(){
        return new DirectExchange(DEAD_EXCHANGE);
    }

    @Bean
    public Queue queueA(){
        Map<String,Object> map = new HashMap();
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key","YD");
        map.put("x-message-ttl",10000);

        return QueueBuilder.durable(QUEUE_A).withArguments(map).build();
    }
    @Bean
    public Queue queueB(){
        Map<String,Object> map = new HashMap();
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key","YD");
        map.put("x-message-ttl",3000);

        return QueueBuilder.durable(QUEUE_B).withArguments(map).build();
    }

    @Bean
    public Queue queueD(){
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Binding QueueABindingX(@Qualifier("queueA") Queue queueA,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }
    @Bean
    public Binding QueueBBindingX(@Qualifier("queueB") Queue queueA,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XB");
    }
    @Bean
    public Binding QueueDBindingY(@Qualifier("queueD") Queue queueA,
                                  @Qualifier("yExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("YD");
    }
    @Bean
    public Binding QueueCBindingC(@Qualifier("queueC") Queue queueA,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XC");
    }
}
