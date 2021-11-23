package com.lichangxin.CAS;


/*
* 使用CAS实现一个线程安全计数器
* CAS 有一个假设，如果期望相同，就认为没有被改变过
* 就是CAS中的ABA问题，和实际情况有关
* 规避此问题可以引入一个时间戳，每次修改时加一
* */
public class Test1 {
    public static void main(String[] args) {
        CASCounter casCounter = new CASCounter();
        for (int i = 0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(casCounter.incrementAndGet());

                }
            }).start();
        }
    }

}
class CASCounter{
    private long value;

    public long getValue() {
        return value;
    }

    private boolean compareAndSwap(long expectionValue,long newValue){
        // 加锁防止此方法在执行过程中被其他线程调用
        synchronized (this){
            if(value == expectionValue){
                value = newValue;
                return true;
            }else{
                System.out.println("不符合预期");
                return  false;
            }
        }
    }
    public long incrementAndGet(){
        long oldValue;
        long newValue;
        do {
         oldValue = value;
         newValue = oldValue + 1;
        }while (!compareAndSwap(oldValue,newValue));
        return newValue;
    }
}
