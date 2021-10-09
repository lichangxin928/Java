package com.lichangxin.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Test {
//    ExecutorService ;
//    ScheduledExecutorService ;
//    Executors
public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    for (int i = 0; i < 18; i++) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println(Thread.currentThread().getId() + "开始执任务，开始时间为：" + System.currentTimeMillis());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

}
