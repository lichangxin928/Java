package com.lcx.handler;

import com.lcx.service.SomeService;
import com.lcx.utils.ServiceTools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    // 目标对象
    Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 通过代理对象执行这个方法时， 会调用执行这个invoke()
        Object res = null;
        if(method.getName() =="doSome"){
            ServiceTools.doLogs();
            // 执行目标类方法，通过method 来实现
            res = method.invoke(target, args);
            ServiceTools.end();
            // 返回
        }

        return res;
    }
}
