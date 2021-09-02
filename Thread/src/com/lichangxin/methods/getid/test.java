package com.lichangxin.methods.getid;

public class test {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " id " + Thread.currentThread().getId() );

        for (int i = 0 ;i<5;i++){
            new getid().start();
        }
    }
}
