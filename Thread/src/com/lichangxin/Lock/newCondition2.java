package com.lichangxin.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class newCondition2 {
    /*
    * 多个Condition 实现通知部分线程
    * */
    static class server{
        private ReentrantLock lock = new ReentrantLock();
        //定义两个 condition 对象
        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();

        // 定义方法使用 ConditionA 等待
        public void waitConitionA(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " begin wait" + System.currentTimeMillis());
                condition1.await();
                System.out.println(Thread.currentThread().getName() + " end wait " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        // 定义方法使用 ConditionB 等待
        public void waitConitionB() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " begin wait" + System.currentTimeMillis());
                condition1.await();
                System.out.println(Thread.currentThread().getName() + " end wait " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        // 唤醒 ConditionA 对象上面的等待
        public void signalA(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " signal A time " + System.currentTimeMillis());
                condition1.signal();
                System.out.println(Thread.currentThread().getName() + " signal A time " + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
        // 唤醒 ConditionB 对象上面的等待
        public void signalB(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " signal B time " + System.currentTimeMillis());
                condition2.signal();
                System.out.println(Thread.currentThread().getName() + " signal B time " + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws  Exception{
        server server = new server();
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.waitConitionA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.waitConitionB();
            }
        }).start();
        Thread.sleep(3000);
        // 唤醒
        server.signalA();
        server.signalB();

    }
}
