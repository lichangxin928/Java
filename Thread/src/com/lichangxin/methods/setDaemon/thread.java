package com.lichangxin.methods.setDaemon;

public class thread extends Thread{
    @Override
    public void run() {
        try {
            while (true){
                System.out.println("sub thread......");
                sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
