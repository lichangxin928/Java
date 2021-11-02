package com.itheima.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Component
@Scope("singleton")
public class LogUtil {

    public LogUtil(){
        System.out.println("对象创建了");
    }

    @PostConstruct
    public void init(){
        System.out.println("对象初始化了");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("对象销毁了");
    }
}
