package com.lichangxin.CAS;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Test2 {
    public static void main(String[] args) throws Exception{
        /*
        * 原子类：
        * 基础数据型：AtomicInteger、AtomicLong、AtomicBoolean
        * 数组型：AtomicIntegerArray、AtomicLongArray、AtomicReferenceArray
        * 字段更新器：AtomicIntegerFieldUpdate、AtomicLongFieldUpdate、AtomicReferenceUpdate
        * 引用型：AtomicReference、AtomicStampedReference、AtomicMarkableReference
        *
        *
        * 使用AtomicLong计数器
        * */

        // 模拟用户请求
        for (int i = 0;i<=10000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Indicator.getINDICATOR().newRequestReceive();
                    int num = new Random().nextInt();
                    if(num % 2 == 0 ){
                        Indicator.getINDICATOR().successReuest();
                    }else{
                        Indicator.getINDICATOR().failureRequest();
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(Indicator.getINDICATOR().getRequestCount());
        System.out.println(Indicator.getINDICATOR().getSuccessCount());
        System.out.println(Indicator.getINDICATOR().getFailureCount());

    }
}
class Indicator{
    // 单例 饿汉式
    private Indicator(){};
    // 定义一个私有的本类静态对象
    private static final Indicator INDICATOR = new Indicator();
    // 提供一个公共静态方法返回唯一实例

    public static Indicator getINDICATOR() {
        return INDICATOR;
    }
    private final AtomicLong requestCount = new AtomicLong(0);
    private final AtomicLong successCount = new AtomicLong(0);
    private final AtomicLong failureCount = new AtomicLong(0);
    // 有新的请求
    public void newRequestReceive(){
        requestCount.incrementAndGet();
    }
    // 成功数
    public void successReuest(){
        successCount.incrementAndGet();
    }
    // 失败数
    public void failureRequest (){
        failureCount.incrementAndGet();
    }

    public long getFailureCount() {
        return failureCount.get();
    }

    public long getRequestCount() {
        return requestCount.get();
    }

    public long getSuccessCount() {
        return successCount.get();
    }
}
