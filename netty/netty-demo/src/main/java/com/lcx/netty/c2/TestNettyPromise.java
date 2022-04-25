package com.lcx.netty.c2;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

public class TestNettyPromise {
    public static void main(String[] args) {

        EventLoop eventLoop = new NioEventLoopGroup().next();

        DefaultPromise<Integer> integerDefaultPromise = new DefaultPromise<>(eventLoop);

        new Thread(()->{
            System.out.println("开始计算");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            integerDefaultPromise.setSuccess(100);
        }).start();

        integerDefaultPromise.addListener(pro->{
            System.out.println(pro.getNow());
        });
    }
}
