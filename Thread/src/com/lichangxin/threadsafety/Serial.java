package com.lichangxin.threadsafety;

public class Serial {
    public static void main(String[] args) {
        /*
        * JIT 编译器，处理器，存储子系统是按照一定的规则对指令，内存
        * 操作的结果进行重排序，给单线程程序造成一种假象————指令是按照源码
        * 的顺序执行的，这种假象被称为貌似串行语义，并不能保证多线程环境程序的正确性
        *
        * 有数据依赖关系的操作不会被串行语句重排序，如果不存在依赖关系，可能会进行重排
        * 控制依赖关系的语句运行重排
        *
        * 保证内存访问的顺序性
        *
        * 可以使用 volatile 和 synchronized 关键字来实现
        *
        * */
    }
}