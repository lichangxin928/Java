package com.lichangxin.readWriteLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class writeLock {
    static class Service {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void write(){
            try{
                readWriteLock.writeLock().lock();
                System.out.println(Thread.currentThread().getName() + "获得了写锁" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.write();
                }
            }).start();
        }
    }
}
