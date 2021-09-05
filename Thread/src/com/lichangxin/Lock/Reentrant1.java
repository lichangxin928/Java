package com.lichangxin.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reentrant1 {
    // 定义显示锁
    static Lock lock = new ReentrantLock();
    // 定义方法
    public static void sm(){
        // 先获得锁
//        lock.lock();
        for(int i = 0 ;i< 100;i++){
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
        // 释放锁
//        lock.unlock();
    }

    public static void main(String[] args) {
        /*
        * ReentrantLock 基本使用
        * 调用 lock()方法获得锁，调用 unlock() 方法释放锁
        * */
        Runnable r = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}