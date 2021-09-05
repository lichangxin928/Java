package com.lichangxin.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock1 {
    /*
    * tryLock() 在给定时间内锁没有被另外的线程持有，并且当前线程也没有被中断，则获得该锁，通过该方法可以实现锁对象的限时等待
    *
    * */
    static class TimeLock implements Runnable{
        private static ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                if(lock.tryLock(3, TimeUnit.SECONDS)){
                    System.out.println(Thread.currentThread().getName() + " 获得了锁");
                    Thread.sleep(4000);
                }else{
                    System.out.println(Thread.currentThread().getName() + " 没有获得锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }
    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }
}
