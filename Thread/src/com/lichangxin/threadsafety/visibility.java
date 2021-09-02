package com.lichangxin.threadsafety;

public class visibility {
    public static void main(String[] args) throws Exception {
        /*
        * 在多线程环境中，一个线程对某个共享变量进行更新后，后续其他的线程可能无法立刻
        * 读取到这个更新的结果，这就是线程安全的另一种形式，可见性
        *
        * 如果一个线程对共享变量更新之后，后续访问该变量的其他线程可以读到更新的结果，称这个线程对共享变量
        * 的更新可见，反之则表示不可见
        *
        * */
        Mytest mytest = new Mytest();
        new Thread(mytest).start();
        Thread.sleep(1000);
//      主线程执行一秒后取消
        mytest.cancel();
    }
    static class Mytest implements Runnable{
        private boolean toCancel = false;
        @Override
        public void run() {
            while(!toCancel){
                if(doSomeThing()){
                    break;
                }
            }
            if(toCancel){
                System.out.println("任务被取消");
            }else{
                System.out.println("任务完成");
            }
        }
        private boolean doSomeThing(){
            System.out.println("执行某个任务");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        public void cancel(){
            toCancel = true;
            System.out.println("收到！！取消");
        }
    }
}
