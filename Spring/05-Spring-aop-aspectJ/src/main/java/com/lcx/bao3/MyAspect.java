package com.lcx.bao3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


/**
 * @Aspect 表示当前类是切面类，这个类中有切面的功能代码
 */
@Aspect
public class MyAspect {
    /**
     * 环绕通知
     * 1. public
     * 2. 必须有一个返回值
     * 3. 方法名称自定义
     * 4. 方法有参数，固有参数ProceedingJoinPoint
     * 特点：
     *  1. 它是功能最强的通知
     *  2. 在目标方法的前后都能增加功能
     *  3. 控制方法是否被调动执行
     *  4. 修改原来的目标方法的执行结果
     *
     * 等同于 jdk 的动态代理
     *
     *
     * 参数：ProceedingJoinPoint 等同于 method
     *  作用是执行目标方法，返回值是目标方法返回值
     */

    @Around("execution(public int com.lcx.bao3.SomeServiceImpl.doSome())")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("环绕通知开始");
        int proceed = (int) pjp.proceed();
        System.out.println("环绕通知结束");
        return ++proceed;
    }


}
