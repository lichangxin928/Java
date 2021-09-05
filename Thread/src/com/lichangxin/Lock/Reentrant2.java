package com.lichangxin.Lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reentrant2 {
    /*
    * 使用 Lock 锁同步不同方法中的同步代码块
    * */
    static Lock lock = new ReentrantLock();

    public static void sm1(){
        // Lock 经常在try代码中获得锁，在finally子句中获得锁
        try{
            // 获得锁
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "--- method 1 " + System.currentTimeMillis());
            Thread.sleep(3000);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void sm2(){
        try{
            // 获得锁
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " --- method 2 " + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("end sleep methods 2");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                sm1();
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                sm2();
            }
        };
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
