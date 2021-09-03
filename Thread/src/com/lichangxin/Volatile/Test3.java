package com.lichangxin.Volatile;

public class Test3 {
    public static void main(String[] args) {
        /*
        * volatile 不具有原子性
        * */
        for(int i=0;i<1000;i++){
            new MyThread().start();
        }
    }
    static class MyThread extends Thread{
        public  static int count;
        public synchronized static void addCount (){
            for (int i= 0 ;i < 1000;i++){
                count ++;
            }
            System.out.println(Thread.currentThread().getName()+"count == " + count);
        }
        @Override
        public void run() {
           addCount();
        }
    }
}
