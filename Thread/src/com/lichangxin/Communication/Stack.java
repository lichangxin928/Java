package com.lichangxin.Communication;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    private List list = new ArrayList();
    private  static final int MAX = 1;

    public synchronized void push(){
        if(list.size() >= MAX){
            System.out.println(Thread.currentThread().getName() + " begin wait");
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        String data = "data -- " + Math.random();
        System.out.println(Thread.currentThread().getName() + " 添加了数据" + data);
        list.add(data);
        this.notify();
    }

    public synchronized void pop (){
        if(list.size() == 0){
            System.out.println(Thread.currentThread().getName() + " begin wait");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "出栈数据" + list.remove(0));
        this.notify();
    }
}
// 生产者线程
class ProduerThread extends Thread{
    private Stack stack;

    public ProduerThread(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true){
            stack.push();
        }
    }
}
// 消费者线程
class ConsumerThread extends Thread{
    private Stack stack;

    public ConsumerThread(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true){
            stack.pop();
        }
    }
}

class Test3{
    public static void main(String[] args) {
        Stack stack = new Stack();
        ProduerThread produerThread = new ProduerThread(stack);
        ConsumerThread consumerThread = new ConsumerThread(stack);
        // 两个线程交替执行
        produerThread.start();
        consumerThread.start();
    }
}