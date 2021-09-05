package com.lichangxin.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class newCondition3 {
    /*
    * 使用Condition实现交替打印
    *
    * */
    static class MyClass{
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private boolean flag = true;

        // 定义打印方法
        public void printOne(){
            try {
                lock.lock();
                while (flag){
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + "--------");
                flag = true;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void printTwo(){
            try {
                lock.lock();
                while (!flag){
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ".........");
                flag = false;
                condition.signal();
                // 当多对多的情况下时，使用 signalALl()
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ;i< 100;i++){
                    myClass.printOne();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    myClass.printTwo();
                }
            }
        }).start();
    }
}
