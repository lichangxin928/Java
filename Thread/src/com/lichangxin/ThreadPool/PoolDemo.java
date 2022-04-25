package com.lichangxin.ThreadPool;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<?> submit =  executorService.submit(() -> {
            System.out.println("submit");
            return 1;
        });
        System.out.println(submit.get());
        executorService.execute(()->{
            System.out.println(1/0);
        });


    }
}
