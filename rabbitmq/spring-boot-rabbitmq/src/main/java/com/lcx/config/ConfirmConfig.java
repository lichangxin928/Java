package com.lcx.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfirmConfig {

    public static final String EXCHANGE_NAME = "confirm_exchange";

    public static final String QUEUE_NAME = "confirm_queue";

    public static final String ROUTING_KEY = "confirm_key";

    public static final String BACKUP_EXCHANGE_NAME = "backup.exchange";

    public static final String BACKUP_QUEUE_NAME = "backup.queue";

    public static final String WARNING_QUEUE_NAME = "warning_queue";

    @Bean
    public DirectExchange confirmExchange(){
        ExchangeBuilder exchangeBuilder =
                ExchangeBuilder.directExchange(EXCHANGE_NAME)
                        .durable(true)
                        .withArgument("alternate-exchange", BACKUP_EXCHANGE_NAME);
        return exchangeBuilder.build();
    }

    @Bean
    public Queue confirmQueue(){
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Binding queueBindExchange(@Qualifier("confirmExchange") DirectExchange exchange,
                                     @Qualifier("confirmQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    @Bean
    public Queue backupQueue(){
        return new Queue(BACKUP_QUEUE_NAME);
    }

    @Bean
    public Queue warningQueue(){
        return new Queue(WARNING_QUEUE_NAME);
    }

    @Bean
    public Binding backupQueueAndBackupExchange(@Qualifier("backupQueue") Queue queue,
                                                @Qualifier("backupExchange")FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

}
