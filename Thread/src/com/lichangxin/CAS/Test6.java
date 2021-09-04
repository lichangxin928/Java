package com.lichangxin.CAS;
import java.util.concurrent.atomic.AtomicReference;

/*
* AtomicReference 可能会出现 ABA 问题
* */
public class Test6 {
    private static AtomicReference<String> atomicReference = new AtomicReference<>("abc");
    public static void main(String[] args) {
        // 先改变为def 再改变会abc
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicReference.compareAndSet("abc","def");
                System.out.println(Thread.currentThread().getName() + "-- "+ atomicReference.get());
                atomicReference.compareAndSet("def","abc");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicReference.compareAndSet("abc","ghg"));
                System.out.println(atomicReference.get());
            }

        });
        t1.start();
        t2.start();
    }
}
