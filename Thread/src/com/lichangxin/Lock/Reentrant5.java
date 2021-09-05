package com.lichangxin.Lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reentrant5 {
    /*
    * 对弈 synchronized 内部锁来说，如果一个线程在等待锁，只用两结果，一个是获得锁继续执行，另一个就是保持等待
    * 对于ReentrantLock 可重入锁来说，提供另外一种可能，在等待锁的过程中，程序可以根据需要取消对锁的请求
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
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + " 获得锁一还需要锁二");
                    Thread.sleep(new Random().nextInt(500));
                    lock2.lockInterruptibly();
                    System.out.println("同时获得了锁一和锁二");
                }else{
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + " 获得锁二还需要锁一");
                    Thread.sleep(new Random().nextInt(500));
                    lock1.lockInterruptibly();
                    System.out.println("同时获得了锁一和锁二");
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
        Thread.sleep(3000);
        // 可以只中断其中一个
//        if(t1.isAlive()){t1.interrupt();}
        if(t2.isAlive()){t2.interrupt();}

    }
}
