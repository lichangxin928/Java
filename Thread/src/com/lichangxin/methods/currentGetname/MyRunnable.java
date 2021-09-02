package com.lichangxin.methods.currentGetname;


public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int i=1;i<=100;i++){
            System.out.println("sub thread " + i);
        }
    }
}
