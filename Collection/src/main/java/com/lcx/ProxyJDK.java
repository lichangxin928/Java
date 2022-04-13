package com.lcx;

import com.lcx.DynamicProxy.JDKInvokeHandler;
import com.lcx.domain.Animal;
import com.lcx.domain.Dog;

import java.lang.reflect.Proxy;

public class ProxyJDK {
    // JdkDynamicAopProxy
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal obj = (Animal) Proxy.newProxyInstance(
                dog.getClass().getClassLoader(),
                dog.getClass().getInterfaces(),
                new JDKInvokeHandler(dog));
        obj.talk();
    }
}
