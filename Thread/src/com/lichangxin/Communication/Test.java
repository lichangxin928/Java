package com.lichangxin.Communication;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        /*
        * wait( long ) 方法
        *
        * 通知过早问题：
        * 在wait() 前就调用了 notify() 方法 就会出现通知过早问题
        *
        * 在使用 wait 条件发生变化时，可能也会造成逻辑的混乱
        * */
        ThreadAdd threadAdd = new ThreadAdd();
        ThreadSub threadSub1 = new ThreadSub();
//        threadSub.start();
//        threadAdd.start();
        ThreadSub threadSub2 = new ThreadSub();

        threadSub1.start();
        threadSub2.start();
        threadAdd.start();


    }
    // 定义 list 集合
    static List list = new ArrayList();
    // 定义方法从 list 集合中取数据
    public static void subtract() throws Exception{
        synchronized (list){
            while(list.size() == 0){
                System.out.println(Thread.currentThread().getName() + " begin wait");
                list.wait();
                System.out.println("end wait");
            }
        }
        // 移除一个数据
        Object data = list.remove(0);
        System.out.println(Thread.currentThread().getName() + " 移除了一个数据" + data);

    }

    // add data
    public static void add(){
        synchronized (list){
            list.add("data");
            list.notifyAll();
        }
    }
    static class ThreadAdd extends Thread{
        @Override
        public void run() {
            add();
        }
    }
    static class ThreadSub extends Thread{

        @Override
        public void run() {
            try {
                subtract();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
