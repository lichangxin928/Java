package com.lichangxin.methods.getName;

public class Mythread2 extends Thread{
    public Mythread2(){
        System.out.println("构造方法中的 currentThread >" + Thread.currentThread().getName());
        System.out.println("构造方法中的 this >" + this.getName());
    }

    @Override
    public void run() {
        System.out.println("run方法中的 currentThread >" + Thread.currentThread().getName());
        System.out.println("run方法中的 this >" + this.getName());
    }
}
