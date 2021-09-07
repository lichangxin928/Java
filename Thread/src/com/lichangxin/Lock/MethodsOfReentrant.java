package com.lichangxin.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class MethodsOfReentrant {
    /*
    * getHoldCount() 返回线程调用 lock() 的次数
    *
    *
    * getQueueLength() 返回正在等待线程的预估数（可能不准确）
    *
    *
    * getWaitQueueLength(Condition condition ) 返回与Condition 条件相关的线程预估数
    *
    *
    * hasQueuedThread(Thread thread) 查询指定的线程是否在等待锁  当没有参数的时候表示是否存在线程在等待锁    *
    *
    *
    * hasWaiters(Condition condition) 查询是否有线程正在等待指定的 Condition 条件
    *
    *
    * isFair() 判断是否为共平锁
    *
    *
    * isHeldByCurrentThread  判断是否被当前线程持有
    *
    *
    * isLocked() 判断当前锁是否被线程持有
    *
    *
    * */

    // 定义锁对象
    static ReentrantLock lock = new ReentrantLock();

    public static void m1(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "hold count " + lock.getHoldCount());
            m2();
        }finally {
            lock.unlock();
        }
    }

    public static void m2(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "hold count " + lock.getHoldCount());
        }finally {
            lock.unlock();
        }
    }

    public static void sm(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁，执行方法，估计等待数 " + lock.getQueueLength());
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        m1();
    }
}
