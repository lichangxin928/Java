package com.lichangxin.Communication;

public class Test2 {
    /*
    * 多生产多消费
    * */
    public static void main(String[] args) {
        ProAndCon proAndCon = new ProAndCon();
        Producer p = new Producer(proAndCon);
        Cons c = new Cons(proAndCon);
        Producer p1 = new Producer(proAndCon);
        Cons c1 = new Cons(proAndCon);
        Producer p2 = new Producer(proAndCon);
        Cons c2 = new Cons(proAndCon);
        p.start();
        p1.start();
        p2.start();
        c.start();
        c1.start();
        c2.start();
    }
}
