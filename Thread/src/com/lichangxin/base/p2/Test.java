package com.lichangxin.base.p2;

/*
*   线程随机性
* */
public class Test {
    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        mythread.start();
//        当前线程
        try {
            for(int i = 0;i<10;i++){
                System.out.println("main thread:"+i);
                int time = (int)(Math.random()*1000);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
