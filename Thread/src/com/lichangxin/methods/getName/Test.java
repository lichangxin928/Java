package com.lichangxin.methods.getName;

public class Test {
    public static void main(String[] args) {
        /*
        * currentThread()方法
        * 获取当前执行的线程
        * java中的任何一段代码都是执行在某个线程中的，指定当前线程的代码就是当前线程
        * */

        System.out.println("main methods:" + Thread.currentThread().getName());
//      main 线程调用的构造方法，所以输出的就是main线程
        Mythread1 mythread1 = new Mythread1();
        mythread1.start();
    }
}
