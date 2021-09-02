package com.lichangxin.methods.sleep;

public class subThread extends Thread{
    @Override
    public void run() {
        try {
            System.out.println("run ThreadName  " + Thread.currentThread().getName()
                            + "begin " + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("end " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
