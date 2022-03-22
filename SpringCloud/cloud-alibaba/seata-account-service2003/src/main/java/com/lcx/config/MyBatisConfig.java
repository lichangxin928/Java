package com.lcx.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.lcx.dao"})
public class MyBatisConfig {
}
