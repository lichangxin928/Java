package com.itheima.event;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 事件源对象
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Component
@DependsOn("eventZListener")
public class EventSource {

    public EventSource(){
        System.out.println("事件源对象创建了");
    }
}
