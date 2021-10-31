package com.lcx.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * basePackages 和 value 互用
 * basePackageClasses 传入字节码文件，将从那个包开始向下扫描
 * nameGenerator 用来初始化Bean 的名称。
 *
 */
@Configuration
@ComponentScan(value = "com.lcx.service")
public class SpringConfiguration {

}
