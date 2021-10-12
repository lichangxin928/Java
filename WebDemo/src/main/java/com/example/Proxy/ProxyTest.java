package com.example.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        // 创建真实对象
        Lenovo lenovo = new Lenovo();

        // 动态代理增强 lenovo 对象
        /**
         * 三个参数：
         *      1. 类加载器：真实对象.getClass().getClassLoader()
         *      2. 接口数组：真实对象.getClass().getInterfaces()
         *      3. new InvocationHandler()
         */
        SellCompute Proxy_len = (SellCompute) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 代理逻辑编写的方法，代理对象调用的所有方法都会触发该方法执行
             *
             * @param proxy  代理对象
             * @param method 代理对象调用的方法被封装为的对象
             * @param args 代理对象调用方法时，传递的实际参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                String obj = (String) method.invoke(lenovo, args);
//                return obj;
                //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
                // 1. 增强参数
                double money = (double) args[0];
                money = money * 0.85;
                String obj = (String) method.invoke(lenovo,money);
                return obj;
            }
        });
        String computer = Proxy_len.Sell(8000);
        System.out.println(computer);
    }
}
