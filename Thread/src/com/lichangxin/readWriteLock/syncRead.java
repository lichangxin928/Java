package com.lichangxin.readWriteLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class syncRead {
    static class Service{
        // 定义读写锁
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        // 定义方法读取数据
        public void read(){
            try{
                readWriteLock.readLock().lock();        // 获得读锁
                System.out.println(Thread.currentThread().getName() + "获得读锁，开始的时间为：" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                readWriteLock.readLock().unlock();
            }
        }

        public static void main(String[] args) {
            Service service = new Service();

            // 创建五个线程调用 read() 方法
            for (int i = 0; i < 5; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        service.read();
                    }
                }).start();
            }
            // 可以同时获得读锁，执行 lock() 后面的代码，每个线程都能够获得读锁
        }
    }

}
