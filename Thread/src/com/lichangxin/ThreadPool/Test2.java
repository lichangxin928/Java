package com.lichangxin.ThreadPool;

import java.sql.Time;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) {
        // 有计划的执行某个任务
        ScheduledExecutorService scheduledExecutorService =  Executors.newScheduledThreadPool(10);

        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "--" + System.currentTimeMillis());
            }
        },2, TimeUnit.SECONDS);

        // 3 秒后执行任务，以每隔2秒执行一次 如果任务执行时长超过了时间间隔，则任务完成后立即执行
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
//                System.out.println(Thread.currentThread().getId() + "---" + System.currentTimeMillis());
            }
        },3,2, TimeUnit.SECONDS);
        // 在上次任务完成后 固定等待时间再执行
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "---" + System.currentTimeMillis());
            }
        },1,2,TimeUnit.SECONDS);
    }
}
