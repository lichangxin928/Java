package com.lcx.netty.c2;

import java.util.concurrent.*;

public class TestJDKFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> fut = executorService.submit(() -> 80);
        System.out.println(fut.get());
    }
}
