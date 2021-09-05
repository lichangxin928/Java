package com.lichangxin.Lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock3 {
    /*
    * 使用 tryLock 来避免死锁
    * */
    static class IntLock implements Runnable{
        public static ReentrantLock lock1 = new ReentrantLock();
        public static ReentrantLock lock2 = new ReentrantLock();
        int lockNum;

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            try {
                if((lockNum % 2) == 1){
                    if(lock1.tryLock()){
                        System.out.println(Thread.currentThread().getName() + " 获得锁一还需要锁二");
                        Thread.sleep(new Random().nextInt(500));
                    }
                    if(lock2.tryLock()){
                        System.out.println("同时获得了锁一和锁二");
                        return;
                    }
                }else{
                   if(lock2.tryLock()){
                       System.out.println(Thread.currentThread().getName() + " 获得锁二还需要锁一");
                       Thread.sleep(new Random().nextInt(500));
                   }
                    if(lock1.tryLock()){
                        System.out.println("同时获得了锁一和锁二");
                        return;
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(lock1.isHeldByCurrentThread())
                    lock1.unlock();
                if(lock2.isHeldByCurrentThread())
                    lock2.unlock();
                System.out.println(Thread.currentThread().getName()+ " 线程退出");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(22);

        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);
        t1.start();
        t2.start();

    }
}
