package com.example.Proxy;

public class Lenovo implements SellCompute{
    @Override
    public String Sell(double money) {
        System.out.println("花费"+money);
        return "Lenovo 电脑";
    }

    @Override
    public void show() {
        System.out.println("show....");
    }
}
