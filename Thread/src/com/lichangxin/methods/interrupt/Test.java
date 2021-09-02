package com.lichangxin.methods.interrupt;

import com.lichangxin.methods.setpriorty.subThread2;

public class Test {
    public static void main(String[] args) {

        subThread1 t1 = new subThread1();
        t1.start();

        for(int i = 0; i<100;i++){
            System.out.println("main --> " + i);
        }
        t1.interrupt();

    }
}
