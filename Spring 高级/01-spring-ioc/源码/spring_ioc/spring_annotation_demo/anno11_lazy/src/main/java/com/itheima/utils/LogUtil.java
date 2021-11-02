package com.itheima.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 模拟记录日志的类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Component
@Scope("prototype")
@Lazy(value=false)
public class LogUtil {

    public LogUtil(){
        System.out.println("LogUtil对象创建了");
    }

    public void printLog(){
        System.out.println("模拟记录日志");
    }
}
