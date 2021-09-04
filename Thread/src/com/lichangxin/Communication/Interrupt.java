package com.lichangxin.Communication;

public class Interrupt {
    public static void main(String[] args) throws Exception {
        /*
        * 当线程处于 wait() 等待状态时，调用 interrupt() 方法会
        * 中断线程的等待，会产生 InterruptedException 异常
        * */
        subThread t1 = new subThread();
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
    private static final  Object LOCK = new Object();
    static class subThread extends Thread{
        @Override
        public void run() {
            synchronized (LOCK){
                try{
                    System.out.println("begin wait..");
                    LOCK.wait();
                    System.out.println("end wait..");
                }catch (Exception e){
                    System.out.println("线程被中断了。。。。");

                }
            }
        }
    }
}
