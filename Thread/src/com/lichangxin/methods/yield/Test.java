package com.lichangxin.methods.yield;

public class Test {
    public static void main(String[] args) {
        subThread t = new subThread();
        t.start();

        long begin = System.currentTimeMillis();
        long sum = 0;
        for(int i = 0;i<1000000;i++){
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("main线程用时：" + (end-begin));
    }
}
