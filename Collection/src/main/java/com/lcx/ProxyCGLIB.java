package com.lcx;

import com.lcx.DynamicProxy.CglibInterceptor;
import com.lcx.domain.Cat;

public class ProxyCGLIB {

    // DynamicAdvisedInterceptor
    public static void main(String[] args) {

        Cat cat = new Cat();
        CglibInterceptor cglibInterceptor = new CglibInterceptor();
        Cat proxy = (Cat) cglibInterceptor.getProxy(cat.getClass());
        proxy.talk();
    }

}
