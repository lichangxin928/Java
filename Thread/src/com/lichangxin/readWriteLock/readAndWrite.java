package com.lichangxin.readWriteLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class readAndWrite {
    static class Service{
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void read(){
            try {
                readWriteLock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "获得了读锁" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + "释放了读锁" + System.currentTimeMillis());
                readWriteLock.readLock().unlock();
            }
        }
        public void write(){
            try{
                readWriteLock.writeLock().lock();
                System.out.println(Thread.currentThread().getName() + "获得了写锁" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + "释放了写锁" + System.currentTimeMillis());
                readWriteLock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        // 定义一个线程读锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        }).start();
        // 定义一个线程写数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        }).start();
    }
}
