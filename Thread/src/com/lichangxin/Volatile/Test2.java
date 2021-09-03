package com.lichangxin.Volatile;

public class Test2 {
    /*
     *
     * volatile 的作用是让变量在多个线程中可见
     *
     * 每个线程都不是直接操作主存，而成操作工作内存，数据刷新不及时，每个线程只看到的自己的工作内存
     *
     * volatile 和 synchronized 比较
     *
     * volatile 关键字是线程同步的轻量级实现，所以 volatile 性能肯定比 synchronized 要好
     * volatile 只能修饰变量 多线程访问 volatile 不会发生阻塞，而 synchronized 可能会阻塞
     * volatile 可以保证数据可见性，但是不能保证原子性 synchronized 都能够保证
     * */
    public static void main(String[] args) throws Exception {
        // 创建print对象
        Print print = new Print();
        new Thread(new Runnable() {
            @Override
            public void run() {
                print.printStringMethod();
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("在 main 线程中修改");
        print.setContinuePrint(false);
        System.out.println("修改完成");

    }
    static class Print{
        private volatile boolean continuePrint = true;

        public Print setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
            return this;
        }

        public void printStringMethod(){
            System.out.println("开始");
            while (continuePrint) {
            }
            System.out.println("结束");

        }
    }
}
