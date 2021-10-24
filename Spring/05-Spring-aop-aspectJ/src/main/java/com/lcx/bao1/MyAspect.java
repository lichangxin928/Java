package com.lcx.bao1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Date;

/**
 * @Aspect 表示当前类是切面类，这个类中有切面的功能代码
 */
@Aspect
public class MyAspect {
    /**
     * 定义方法，实现切面功能
     *  1. 功能方法，public
     *  2. 方法没有返回值
     *  3. 方法可以有参数，也可以没有参数
     *  4. 方法名称自定义
     */

    /**
     *
     * @Before 前置通知注释
     *  属性 value 是切入点表达式，表示执行的位置
     *  位置 在方法的上面
     */

    /**
     *
     * JoinPoint 切入点
     * 代表的业务方法
     *  作用是：可以在通知方法中获取方法的执行信息，例如方法名称，方法的实际参数
     *  如果你的切面需要用到方法的信息，就需要加入 JoinPoint 并且必须加到第一个位置
     */
    @Before(value = "execution(public void com.lcx.bao1.SomeServiceImpl.doSome())")
    public void myBefore(JoinPoint jp){
        System.out.println("方法的签名：" + jp.getSignature());
        System.out.println("方法名称 : " + jp.getSignature().getName());
        for (Object arg : jp.getArgs()) {
            System.out.println(arg);
        }
        System.out.println("当前时间为：" + new Date());
    }
}
