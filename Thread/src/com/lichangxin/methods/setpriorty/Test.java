package com.lichangxin.methods.setpriorty;

public class Test {
    public static void main(String[] args) {
        /*
        * 设置线程优先级 1-10 线程优先级越高，获取的cpu资源的几率越大，不能保证优先级高的线程先进行
        * java优先级设置不当可能会导致线程永远不被调用，即线程饥饿
        *
        * 线程优先级具有继承性，如在a线程中设置的优先级，那么在a线程创建的线程也是a的优先级
        * */
        subThread1 t1 = new subThread1();
        t1.setPriority(1);
        t1.start();

        subThread2 t2 = new subThread2();
        t2.setPriority(10);
        t2.start();
    }
}
