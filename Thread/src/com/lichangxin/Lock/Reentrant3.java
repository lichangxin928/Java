package com.lichangxin.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reentrant3 {
    private static Lock lock = new ReentrantLock();
    static class SubThread extends Thread{

        public static int num = 0;
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                num++;
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(SubThread.num);
    }
}
