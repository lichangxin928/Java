package com.lichangxin.Communication;

public class Test1 {
    /*
    * 一生产一消费
    *
    * */

        public static void main(String[] args) {
            ProAndCon proAndCon = new ProAndCon();
            Producer p = new Producer(proAndCon);
            Cons c = new Cons(proAndCon);

            p.start();
            c.start();
        }

}
