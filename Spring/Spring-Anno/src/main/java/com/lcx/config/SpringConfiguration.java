package com.lcx.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan(value = "com.lcx")
@Import({JdbcConfiguration.class,TransactionManagerConfig.class})
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfiguration {
}
