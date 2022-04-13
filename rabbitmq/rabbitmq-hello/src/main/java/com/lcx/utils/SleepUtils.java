package com.lcx.utils;

public class SleepUtils {
    public static void sleep(Long second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
