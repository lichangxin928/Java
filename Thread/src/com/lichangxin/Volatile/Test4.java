package com.lichangxin.Volatile;

import java.util.concurrent.atomic.AtomicInteger;

public class Test4 {
    public static void main(String[] args) {
        /*
        * volatile 不具有原子性
        * */
        for(int i=0;i<100;i++){
            new MyThread().start();
        }
    }
    static class MyThread extends Thread{
        // 使用 AtomicInteger
        private static AtomicInteger count = new AtomicInteger();

        public static void addCount (){
            for (int i= 0 ;i < 1000;i++){
                // 自增的原子性
                count.getAndIncrement();
            }
            System.out.println(Thread.currentThread().getName()+"count == " + count.get());
        }
        @Override
        public void run() {
           addCount();
        }
    }
}
