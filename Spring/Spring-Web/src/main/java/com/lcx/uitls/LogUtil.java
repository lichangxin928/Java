//package com.lcx.uitls;//package com.lcx.uitls;
//
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component("aspectLog")
//public class LogUtil {
//
//    @Pointcut("execution(* com.lcx.service.impl.*.*(..))")
//    public void servicePoint(){};
//
//    @Before("servicePoint()")
//    public void serviceBefore(){
//        System.out.println("前置通知");
//    }
//
//    @After("servicePoint()")
//    public void serviceAfter(){
//        System.out.println("最终通知");
//    }
//    @AfterReturning("servicePoint()")
//    public void serviceAfterReturning(){
//        System.out.println("后置通知");
//    }
//    @AfterThrowing("servicePoint()")
//    public void serviceThrowing(){
//        System.out.println("异常通知");
//    }
////    @Around("servicePoint()")
////    public Object serviceAround(ProceedingJoinPoint pjp){
////
////    }
//}
