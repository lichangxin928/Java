package com.lcx;

// 模板方法
public abstract class Test {
    public static void main(String[] args) {
        son son = new son();
        son.A();
    }

}

abstract class father {
    public void A(){
        System.out.println("a");
        B();
    }
    public void B(){}
}
class son extends father{
    @Override
    public void B() {
        System.out.println("b");
    }
}

