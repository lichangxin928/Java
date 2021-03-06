package com.lichangxin.sync;

public class Test {
    public static void main(String[] args) {
        /*
        * 线程同步机制是一套用于协调线程之间的数据访问的机制，该机制能够保证线程安全
        * Java平台提供的线程同步机制包括：锁 volatile 关键字，final static
        * 以及object.wait()/object.notify()
        *
        * 锁概述：
        * 线程安全问题的产生前提是多个线程并发访问共享数据
        * 将多个线程贵共享数据的并发访问转化为串行访问，既一个共享数据只能被一个线程访问
        *
        * JVM把锁分为内部锁和显示锁两种，内部锁通过 synchronized 关键字来实现，
        * 显示锁通过 java.concurrent.locks.Lock 接口的实现类来实现
        *
        * 锁的作用：
        * 锁可以实现对共享数据的安全访问，保障线程的原子性，可见性于有序性
        * 一个锁只能被一个线程持有，其他线程只能等待锁被释放的时候才能得到锁
        * 锁的获得隐含着刷新处理器缓存的动作，锁的释放隐含着冲刷处理器缓存的动作
        * 这样就会第一时间更新数据
        * （冲刷处理器缓存指的是将缓存的数据刷新到主内存中
        *   刷新处理器缓存是将主存的数据读取到缓存区中）
        *
        * 死锁
        * 当需要获得多个锁时，所有线程获得锁的顺序保持一致就不会有死锁的问题
        * */
        subThread t1 = new subThread();
        t1.setName("a");
        subThread t2 = new subThread();
        t2.setName("b");
        t1.start();
        t2.start();


    }
    static class subThread extends Thread{
        private static final Object Lock1 = new Object();
        private static final Object Lock2 = new Object();

        @Override
        public void run() {
            if("a".equals(Thread.currentThread().getName())) {
                synchronized (Lock1) {
                    System.out.println("线程 a 获得了 Lock1 还需要获得 Lock2");
                    synchronized (Lock2) {
                        System.out.println("线程 a 获得了所有资源");
                    }
                }
            }
            if("b".equals(Thread.currentThread().getName())){
                synchronized (Lock2) {
                    System.out.println("b 线程获得了 Lock2 还需要获得 Lock1");
                    synchronized (Lock1){
                        System.out.println("b 线程获得了Lock1 能够运行");
                    }
                }
            }

        }
    }
}
