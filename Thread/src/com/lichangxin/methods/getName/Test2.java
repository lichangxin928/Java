package com.lichangxin.methods.getName;

public class Test2 {
    public static void main(String[] args) throws Exception{
        Mythread2 mythread2 = new Mythread2();
//      设置线程名称
        //thread.getName()/thread.setName() 获取线程
        //thread.isAlive() 判断线程是否处于活跃状态
        mythread2.setName("t2");
        mythread2.start();

        Thread.sleep(500);

        Thread t3 = new Thread(mythread2);
        t3.start();
    }
}
