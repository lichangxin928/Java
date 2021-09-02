package com.lichangxin.threadsafety;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
    public static void main(String[] args) {
        /*
        * 主要是指多个线程对同一个对象的实例进操作
        *
        * 原子性
        * Atomic 不可分割，对多个线程访问同一个变量
        * 对变量操作要么执行完毕要么还没有发生，且不能交替操作
        * Java 有两种方式，一种就是使用 锁 还有就是利用处理器的 CAS
        *
        * */
//        System.out.println("....");
        Myclass myclass = new Myclass();

        for(int i = 1;i<=2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.println(Thread.currentThread().getName() + "-> "+ myclass.getNum());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
    static class Myclass{
        AtomicInteger num = new AtomicInteger();
        public int getNum(){
            return num.getAndIncrement();
        }
    }
}
