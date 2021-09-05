package com.lichangxin.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class newCondition1 {
    /*
    * 关键字 synchronized 与 wait()/notify() 这两个方法一起使用可以实现等待/通知模式
    * Lock 锁的 newCondition() 方法返回 Condition 对象 Condition 也可以实现 等待/通知模式
    * 使用 notify() 通知时，jvm 会随机唤醒某个等待的线程，使用 Condition 类可以进行选择性通知
    *
    * Condition
    * await() 会使当前线程等待，同时会释放锁，当其他线程调用 signal() 时
    * 线程会重新获得锁并继续执行， signal() 用于唤醒一个等待的线程
    *
    * 在调用 await() / signal() 方法钱，也需线程持有相关的 Lock 锁，调用 await() 后
    * 线程会释放这个锁，在signal() 调用会会从当前 Condition 对象的等待队列中，唤醒一个线程
    * 唤醒的线程尝试获得锁，一旦获取成功就会执行该线程
    *
    * */

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    static class SubThread extends Thread{
        @Override
        public void run() {
            try{
                lock.lock();
                System.out.println("method lock");
                condition.await();
                System.out.println("method await");
            }catch(InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("methods unlock");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SubThread t = new SubThread();
        t.start();
        Thread.sleep(3000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }

}
