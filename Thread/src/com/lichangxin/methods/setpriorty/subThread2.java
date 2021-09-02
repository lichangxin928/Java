package com.lichangxin.methods.setpriorty;

public class subThread2 extends Thread{
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        int sum = 0;
        for(int i = 0 ;i<1000000;i++) {
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("thread2 time --> " + (end-begin));
    }
}
