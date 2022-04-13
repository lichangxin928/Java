package com.lcx.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvokeHandler implements InvocationHandler {

    private Object object;

    public JDKInvokeHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object obj = method.invoke(object, args);
        System.out.println("after");

        return obj;
    }
}
