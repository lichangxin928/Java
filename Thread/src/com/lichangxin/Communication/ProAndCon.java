package com.lichangxin.Communication;

public class ProAndCon {
    /*
    * 生产者消费者模型中唤醒后是从wait()处的代码继续执行
    * */
    private String  value = "";
    public static void main(String[] args) {
        /*
        *
        * 生产者消费者模式
        *
        * */
    }
    public void setValue() {
        synchronized (this){
            while (!value.equalsIgnoreCase("")){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String value = System.currentTimeMillis() + "--" + System.nanoTime();
            System.out.println("set 设置的值为： " + value);
            this.value = value;
            this.notifyAll();


        }

    }
    public void getValue(){
        synchronized (this){
            while (value.equalsIgnoreCase("")){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("get 的值为" + this.value);
            this.value = "";
            this.notifyAll();

        }
    }
}
class Producer extends Thread{
    private ProAndCon obj;
    public Producer(ProAndCon obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            obj.setValue();
        }

    }
}
class Cons extends Thread{
    private ProAndCon obj;
    public Cons(ProAndCon obj){
            this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            obj.getValue();
        }
    }
}

