package com.lichangxin.Communication;

import java.sql.SQLOutput;

public class Notify {
    public static void main(String[] args) {
        String lock = "lcx";
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock){
                        System.out.println("线程 t1 开始" + System.currentTimeMillis());
                        lock.wait();
                        System.out.println("线程 t1 结束等待 " + System.currentTimeMillis());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("线程 t2 开始" + System.currentTimeMillis());
                    lock.notify();
                    System.out.println("线程 t2 结束" + System.currentTimeMillis());
                }


            }
        });
        t1.start();
        t2.start();

    }
}
