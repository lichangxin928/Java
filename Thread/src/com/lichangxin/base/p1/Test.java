package com.lichangxin.base.p1;

    public class Test {
        public static void main(String[] args) {
            System.out.println("main 方法");
            Mythread mythread = new Mythread();
            mythread.start();
        }
}