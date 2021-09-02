package com.lichangxin.methods.isalive;

public class Test {
    public static void main(String[] args) {
        subThread t = new subThread();
        System.out.println("begin== " + t.isAlive());
        t.start();
        //不确定 结束了就位false
        System.out.println("end== " + t.isAlive());

    }
}
