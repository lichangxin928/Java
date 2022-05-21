package com.lcx.Cache.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.lcx.Cache.domain")
@EnableAspectJAutoProxy
public class SpringConfiguration {
}
