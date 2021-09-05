package com.lichangxin.Lock;

public class Test {
    public synchronized void sml1(){
        System.out.println("同步一");
        sml2();
    }
    private synchronized void sml2(){
        System.out.println("同步二");
        sml3();
    }
    private synchronized void sml3(){
        System.out.println("同步三");
    }
    public static void main(String[] args) {
        /*
        * JDK5  中增加了LOCK 锁接口
        * 锁的可重入性
        * */
        Test test = new Test();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.sml1();
            }
        }).start();
    }
}
