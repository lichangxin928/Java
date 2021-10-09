package com.lichangxin.threadGroup;

public class Test01 {
    public static void main(String[] args) {
        // 返回 main 线程的线程组
        ThreadGroup threadGroup =  Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup);

        // 定义线程组
        ThreadGroup threadGroup1 = new ThreadGroup("Group1");
        System.out.println(threadGroup1);

        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup,"group2");
        System.out.println(threadGroup2);
        // 现在都是属于 main 线程的线程组中

        // 在创建线程时指定线程组

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        };
        Thread thread = new Thread(r);
        System.out.println(thread);
    }
}
