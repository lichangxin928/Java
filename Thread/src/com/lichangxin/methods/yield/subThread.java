package com.lichangxin.methods.yield;

public class subThread extends Thread {
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for(int i = 0;i<1000000;i++){
            sum+=i;
            Thread.yield();
        }
        long end = System.currentTimeMillis();
        System.out.println("子线程用时：" + (end-begin));
    }
}
