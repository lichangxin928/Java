package com.lichangxin.methods.currentGetname;

/*
* runable 接口来实现
* 当线程以及有父类时，就不能使用继承，这时需要使用实现接口
*
* */
public class Test {
    public static void main(String[] args) {
//      创建一个线程对象
        MyRunnable myRunnable = new MyRunnable();
//      实参对象
        Thread thread = new Thread(myRunnable);
        thread.start();
//      匿名内部类
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
                    System.out.println("sub thread2 " + i);
                }
            }
        });
        thread2.start();
//      同时运行

    }
}
