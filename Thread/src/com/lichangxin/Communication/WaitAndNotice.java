package com.lichangxin.Communication;

public class WaitAndNotice {
    public static void main(String[] args) {
        /*
        * 等待通知机制
        * Object.wait() 可以使执行当前代码的线程等待，暂停执行
        * wait() 方法只能在同步代码块中由锁对象来调用
        * 调用了wait() 方法过后，当前线程会释放锁
        *
        * synchronized (锁对象){
        *   while( 条件不成立 ){
        *       锁对象.wait();
        *   }
        * }
        *
        * Object.notify();
        * 可以唤醒线程，该方法也必须出现在同步代码块中由锁对象嗲欧诺个，没有使用锁对象调用
        * 会抛出异常 如果有多个等待的线程，notify() 方法只会唤醒其中一个。并且调用了
        * notify() 方法不会立即释放锁对象，要等到当前同步代码块执行完过后才会释放，
        * 所以一般情况下回将notify() 方法放在最后
        *
        * 任何对象都能调用 wait() 和 notify() 方法 但是必须在同步代码块内
        * 否则会抛出 IllegalMonitorStateException
        * */

//        try {
//            String test = "lcx";
//            test.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            String test = "lcx";
            String test2 = "aaa";
//            String test1 = "lcx";
            System.out.println("同步代码块前面");

            synchronized (test2){
                test.notify();
            }
            synchronized (test2) {
                System.out.println("同步代码块开始...");
                test.wait();
                // 当前线程会一直等待
                System.out.println("wait 后面的代码，，，");
            }
            System.out.println("同步代码块后面的方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main 最后面的代码");
    }
}
