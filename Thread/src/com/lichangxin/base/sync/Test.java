package com.lichangxin.base.sync;

public class Test {


    class inner{
        private String a;

    }


    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        new Thread(()->{
            try {
                test.test1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        Thread.sleep(10);
        new Thread(()->{
            test.test2();
        }).start();
    }

    public synchronized void test1() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("test1......");
    }

    public synchronized void test2(){
        System.out.println("test2.......");
    }
}

 class test {
    public static void main(String[] args) {
        System.out.println("return value of getValue(): " +
                getValue());
    }
    public static int getValue() {
        try {
            return 0;
        } finally {
            return 1;
        }
    }
}
