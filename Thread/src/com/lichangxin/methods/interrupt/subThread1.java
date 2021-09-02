package com.lichangxin.methods.interrupt;

public class subThread1 extends Thread{
    @Override
    public void run() {

        int sum = 0;
        for(int i = 0 ;i<1000000;i++) {
            if(this.isInterrupted()){
                System.out.println(this.isInterrupted());
                break;
            }
            sum+=i;
            System.out.println("subthread -->" + i);
        }

    }
}
