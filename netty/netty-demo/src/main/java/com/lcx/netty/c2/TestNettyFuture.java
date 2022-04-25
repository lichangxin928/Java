package com.lcx.netty.c2;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;


@Slf4j
public class TestNettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(2);
        EventLoop eventLoop = group.next();

        Future<Integer> future = eventLoop.submit(() -> 90);
//        System.out.println(future.get());
        future.addListener(future1 -> System.out.println(future1.getNow())); // 异步执行
    }
}
