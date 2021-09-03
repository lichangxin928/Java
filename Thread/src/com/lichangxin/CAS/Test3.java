package com.lichangxin.CAS;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Test3 {
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
    public static void main(String[] args) {
        /*
        * AtomicIntegerArray 基本操作
        * */
//        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
//        System.out.println(atomicIntegerArray);
//        System.out.println(atomicIntegerArray.get(0));
//        System.out.println(atomicIntegerArray.addAndGet(1,2));
        Thread[] threads = new Thread[10];
        for (int i = 0 ;i< 10;i++){
            threads[i] = new AddThread();
        }
        // 开启子线程
        for (Thread thread : threads){
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(atomicIntegerArray);


    }
    static class AddThread extends Thread{
        @Override
        public void run() {
            // 自增 1000 次
            for(int i = 0 ;i < 10000;i++){
                atomicIntegerArray.getAndIncrement(i % atomicIntegerArray.length());
            }

        }
    }
}
