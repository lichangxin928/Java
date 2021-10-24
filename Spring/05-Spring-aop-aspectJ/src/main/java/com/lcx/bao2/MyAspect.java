package com.lcx.bao2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Aspect 表示当前类是切面类，这个类中有切面的功能代码
 */
@Component("myAspect")
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
     * 后置通知
     *
     * @AfterReturning:后置通知
     *      属性：1.value 切入点表达式
     *           2.returning 自定义变量，表示目标方法的返回值
     *           自定义变量名必须和通知方法的形参名一样
     *      位置：在方法定义的上面
     *      特点：
     *          1. 在目标方法之后执行
     *          2. 能够获取到目标方法的返回值
     *          3. 可以修改这个返回值
     *
     */
    @AfterReturning(value = "execution(* com.lcx.bao2.SomeServiceImpl.doSome(..))",
                    returning = "res")
    public void myAfterReturning(JoinPoint jp, Object res){
        System.out.println(jp.getSignature().getName());
        res = 2;

    }

}
