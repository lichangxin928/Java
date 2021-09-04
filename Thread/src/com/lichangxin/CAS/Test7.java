package com.lichangxin.CAS;

import java.util.concurrent.atomic.AtomicStampedReference;

/*
* AtomicStampedReference 可以解决 ABA 问题
* 在这个类中，有一个整数标记值为 stamp ，每次进行操作时都要比较版本号
* */
public class Test7 {
    // 指定初始版本号为0
    private static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("abc",0);

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicStampedReference.compareAndSet("abc","def",atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName() + "---" + atomicStampedReference.getReference());
                atomicStampedReference.compareAndSet("def","abc",atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicStampedReference.compareAndSet("abc","ggg",stamp,atomicStampedReference.getStamp()+1);
                System.out.println(atomicStampedReference.getReference());
            }
        });
        t1.start();
        t2.start();
    }
}
