package com.lichangxin.methods.getid;

public class getid extends Thread{
    /*
    * thread.getId
    * 每一线程都有一个唯一编号，可以通过这个方法来获取
    * */
    //线程执行结束过后id可能会重新分配给其他线程
    @Override
    public void run() {
        System.out.println("thread name -->" + Thread.currentThread().getName() + "thread id --> " + this.getId());
    }
}
