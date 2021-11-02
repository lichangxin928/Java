package com.itheima.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 自定义规则
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class LinuxCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1.获取环境信息（为了取出当前操作系统是windows还是linux）
        Environment environment = context.getEnvironment();
        //2.获取当前系统的名称
        String osName = environment.getProperty("os.name");
        //3.判断是否包含windos规则
        osName="os.Linux";
        if(osName.contains("Linux")){
            //需要注册到ioc容器中
            return true;
        }
        //不需要注册到Ioc容器中
        return false;
    }
}
