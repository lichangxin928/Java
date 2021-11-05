package com.lcx.config;

import com.lcx.controller.HelloController;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan(value = "com.lcx",
        excludeFilters =
        @ComponentScan.Filter(type = FilterType.ANNOTATION,
                              classes = Controller.class))
@Import({JdbcConfiguration.class,TransactionManagerConfig.class})
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfiguration {
}
