package com.lcx.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class MyDataSource {

    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource DruidDataSource(){
        return new DruidDataSource();
    }
}
