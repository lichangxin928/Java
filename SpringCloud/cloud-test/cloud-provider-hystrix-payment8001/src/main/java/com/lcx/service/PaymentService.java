package com.lcx.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

    /**
     * 正常访问的方法
     * @param id
     * @return
     */
    // 服务降级，fallback
    public String paymentInfo_OK(Integer id){
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_OK" + id;
    }
//
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String paymentInfo_timeOut(Integer id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_timeOut " + id + " 超时3秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){

        return id + " 请求超时";
    }


    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("id不能为负数");
        }else{
            return "调用成功 id 为 " + id;
        }
    }

    public String paymentCircuitBreaker_fallback(Integer id){

        return "id 不能为负数  " + id;
    }
}
