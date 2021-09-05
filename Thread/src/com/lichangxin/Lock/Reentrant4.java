package com.lichangxin.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reentrant4 {
    /*
    * lockInterruptibly()
    * 如果当前 线程未被中断则获得锁，如果当前线程被中断则出现异常
    * */
    static class Server {
        private ReentrantLock lock = new ReentrantLock();
        public void serverMethods() {
            try{
                lock.lockInterruptibly(); // 如果线程没有中断，则获得锁正常执行，如果被中断了就不会获得锁，抛出异常
                System.out.println(Thread.currentThread().getName() + " --- begin lock");
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    new StringBuilder();
                }
                System.out.println(Thread.currentThread().getName()+ " --- end lock");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                if(lock.isHeldByCurrentThread())
                lock.unlock();
                System.out.println(".......");
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                server.serverMethods();
            }
        };
        Thread t1 = new Thread(r);
        t1.start();
        Thread.sleep(50);
        Thread t2  =new Thread(r);
        t2.start();
        Thread.sleep(50);
        t2.interrupt();
    }
}
