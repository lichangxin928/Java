package com.lichangxin.CAS;

import java.util.concurrent.atomic.AtomicReference;

public class Test5 {
    static AtomicReference<String> atomicReference = new AtomicReference<>("abc");
    public static void main(String[] args) {
        /*
        * AtomicReference 原子读写一个对象
        * */
        for (int i = 0;i < 100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(atomicReference.compareAndSet("abc","def")){
                        System.out.println(Thread.currentThread().getName() + "修改了字符串为def");
                    }
                }
            }).start();
        }
        for (int i = 0;i < 100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(atomicReference.compareAndSet("def","abc")){
                        System.out.println(Thread.currentThread().getName() + "修改了字符串为abc");
                    }
                }
            }).start();
        }
    }
}
