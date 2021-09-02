package com.lichangxin.methods.getName;

public class Mythread1 extends Thread{

    public  Mythread1(){
        System.out.println("constructor methods:" + Thread.currentThread().getName());
    }
    @Override
    public void run() {
        System.out.println("run methods:" + Thread.currentThread().getName());
    }
}
