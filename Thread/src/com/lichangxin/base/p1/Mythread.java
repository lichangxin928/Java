package com.lichangxin.base.p1;

public class Mythread extends Thread{
//    重写run方法

    @Override
    public void run() {
        super.run();
        System.out.println("这是子线程打印的内容");
    }
}
