package com.lcx;

import com.lcx.handler.MyInvocationHandler;
import com.lcx.service.SomeService;
import com.lcx.service.impl.SomeServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyApp {
    public static void main(String[] args) {
        // 创建目标对象
        SomeService target = new SomeServiceImpl();

        // 创建 InvocationHandler对象
        MyInvocationHandler handler = new MyInvocationHandler(target);

        // 创建代理对象
        SomeService proxy = (SomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),handler);

        // 通过代码执行方法，会调用handler 中的 invoke()
        proxy.doSome();
    }
}
