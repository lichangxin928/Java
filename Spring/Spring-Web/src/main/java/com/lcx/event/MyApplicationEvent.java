package com.lcx.event;

import org.springframework.context.ApplicationEvent;

public class MyApplicationEvent extends ApplicationEvent {

    Object source;
    public MyApplicationEvent(Object source) {
        super(source);
        System.out.println(source);
        this.source = source;
    }
}
