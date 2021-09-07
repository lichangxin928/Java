package com.lichangxin.Volatile;

public class Test1 {
    /*
    *
    * volatile 的作用是让变量在多个线程中可见
    * */
    public static void main(String[] args) throws Exception {
        // 创建print对象
        Print print = new Print();
        print.printStringMethod();

        Thread.sleep(1000);
        System.out.println("在 main 线程中修改");
        print.setContinuePrint(false);
        System.out.println("修改完成");

    }
    static class Print{
        private boolean continuePrint = true;

        public Print setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
            return this;
        }

        public void printStringMethod(){
            while (continuePrint) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
