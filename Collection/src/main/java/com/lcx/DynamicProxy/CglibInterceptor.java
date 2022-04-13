package com.lcx.DynamicProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibInterceptor implements MethodInterceptor {

    public Object getProxy(Class targetClass) {
        Enhancer enhancer = new Enhancer();
        //设置回调对象
        enhancer.setCallback(this);
        //设置父类为被代理的对象
        enhancer.setSuperclass(targetClass);
        //创建子类实例（代理对象）
        return enhancer.create();
    }

    /**
     *
     * @param o  代理对象
     * @param method  被调用的方法
     * @param objects  方法的参数
     * @param methodProxy  方法的代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object reVal = methodProxy.invokeSuper(o, objects);
//        Object reVal = method.invoke(o, objects);
        System.out.println("after");
        return reVal;
    }
}
