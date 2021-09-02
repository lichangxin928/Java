package com.lichangxin.more.life;

public class Test {
    public static void main(String[] args) {
        /*
        * 线程的生命周期就是线程的状态，可以通过getState()方法获得
        *
        * Thread.State 枚举类型
        * NEW 新建状态，在start 之前的状态
        * RUNNABLE 可运行状态，它是一个符合状态，包含:READY 和 RUNNING 两个状态
        * RUNNNING 表示该线程正在运行 READY 表示该线程能够被线程调度器调度
        * Thread，yield() 方法能够让线程从RUNNING状态变成READY状态
        * BLOCKED 阻塞状态，线程发起阻塞的I/O操作或者申请其他资源时就会变成此状态，当获得了
        * 所需要的资源时，救护变成RUNABLE 状态
        * WAITING 等待状态， 线程执行了 object.wait() thread.join() 方法会吧线转换为WAITING 状态，
        * 执行object.notify() 方法，或者加入的线程执行完毕，当前线程会转换为RUNNABLE状态
        * TIMED_WAITING 与WAITING状态类似，都是等待状态，但是不会永远等待，如果在规定的时间
        * 没有完成期望的操作，就会自动转换为RUNNABLE
        * TERMINATED 线程结束
        *
        * */
    }
}
