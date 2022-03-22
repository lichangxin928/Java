package com.lcx.utils;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy {
    public static void main(String[] args) {
        test test = new test();
        Object o = Proxy.newProxyInstance(new MyProxy().getClass().getClassLoader(), test.getClass().getInterfaces(), new A());
        System.out.println(o);
    }
}interface say{
    void say();
}class test implements say{
    public void say(){
        System.out.println("hello");
    };
}
class A implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        return "12132";
    }
}