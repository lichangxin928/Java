package com.lcx.ThreadPool;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolSource {
    public static void main(String[] args) {
        Executors.newFixedThreadPool(10);
    }
}
