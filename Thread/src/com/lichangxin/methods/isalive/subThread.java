package com.lichangxin.methods.isalive;

public class subThread extends Thread{
    @Override
    public void run() {
        System.out.println("run 方法，isAlive " + this.isAlive() );
    }
}
