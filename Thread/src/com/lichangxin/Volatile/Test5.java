package com.lichangxin.Volatile;

public class Test5 {
    /*
    * CAS compare and swap 硬件来实现的
    * CSA 可以将 read - modify - write 这类操作转换为原子操作
    *
    * i++ 自增包括三操作
    * 从主存取 i
    * 对 i 加一
    * 将 i 保存到主存
    *
    * CAS 原理：在吧数据更新到主内存时，再读取主内存变量的值，如果现在变量的值
    * 与期望的值（操作开始前的值）一样就更新
    *
    * */
}
