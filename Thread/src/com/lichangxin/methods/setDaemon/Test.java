package com.lichangxin.methods.setDaemon;

public class Test {
    public static void main(String[] args) throws Exception{
        /*
        * 守护线程是为其他线程提供服务的线程，如垃圾回收器
        * 守护线程不能单独运行，当没有其他用户线程时，守护线程会自动退出
        * */
        thread t = new thread();
        t.setDaemon(true);
        t.start();

        for(int i = 0 ;i< 100;i++){
            System.out.println("main== " + i);
            Thread.sleep(500);
        }
    }
}
