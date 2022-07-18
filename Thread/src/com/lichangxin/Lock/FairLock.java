package com.lichangxin.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock {
    /*
    * 公平锁与非公平锁
    * synchronized 内部锁就是非公平的 ReentrantLock 重入锁提供了一个构造方法
    * 当调用无参数的a构造方法，就是非公平锁，当传入的是true时就是公平锁
    *
    * 成本高
    *
    * */
    static ReentrantLock reentrantLock = new ReentrantLock(false); // 非公平锁
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        reentrantLock.lock();
                        System.out.println(Thread.currentThread().getName() + " 获得了锁");
                        Thread.sleep(100000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
