package com.lichangxin.ThreadPool;


import java.util.concurrent.*;

public class PoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Callable callable = new Callable() {

            @Override
            public Object call() throws Exception {
                throw new RuntimeException();
            }
        };
        System.out.println("callable");
        Future submit = executorService.submit(callable);
//        System.out.println(submit.get());
        Thread thread = new Thread(){
            @Override
            public void run() {
//                throw new RuntimeException();
            }
        };
        System.out.println("runnable");
        Future<?> submit1 = executorService.submit(thread);
        submit1.get();

    }
}
