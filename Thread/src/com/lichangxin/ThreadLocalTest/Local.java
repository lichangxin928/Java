package com.lichangxin.ThreadLocalTest;

public class Local {

    // 定义ThreadLocal对象
    static ThreadLocal threadLocal  = new ThreadLocal();
    // 定义线程类
    static class SubThread extends Thread {
        @Override
        public void run() {
            for(int i = 0 ;i < 20 ;i++){
                // 设置线程关联的值
                threadLocal.set(Thread.currentThread().getName() + "-" + i);
                // 调用get() 方法来读取关联的值
                System.out.println(Thread.currentThread().getName()+ " value = " + threadLocal.get());
            }
        }
    }
    public static void main(String[] args) {
        /*
        * 除了控制资源的访问外，还能够通过增加资源来保证线程安全 ThreadLocal 主要解决为每个线程绑定自己的值
        * ThreadLocal 基本使用
        * */
    }
}
