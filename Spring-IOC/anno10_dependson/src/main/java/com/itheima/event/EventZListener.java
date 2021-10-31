package com.itheima.event;

import org.springframework.stereotype.Component;

/**
 * 监听器
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Component
public class EventZListener {

    public EventZListener(){
        System.out.println("监听器创建了");
    }
}
