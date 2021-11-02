package com.itheima.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 自定义注册bean的条件，windows操作系统注入
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class WindowsCondition implements Condition {

    /**
     * 是否注册到ioc容器中的核心方法
     * @param context  ioc上下文对象
     * @param metadata
     * @return  是true的时候，表示注册到ioc容器中，否则不注册
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1.获取ioc使用的BeanFactory对象
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //2.获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //3.获取环境信息（为了取出当前操作系统是windows还是linux）
        Environment environment = context.getEnvironment();
        //输出所有的系统环境信息
        if(environment instanceof StandardEnvironment){
            //转换环境信息
            StandardEnvironment standardEnvironment = (StandardEnvironment)environment;
            Map<String,Object> map = standardEnvironment.getSystemProperties();
            for(Map.Entry<String,Object> me : map.entrySet()){
                System.out.println(me.getKey()+","+me.getValue());
            }
        }

        //4.获取bean定义信息的注册器
        BeanDefinitionRegistry registry = context.getRegistry();
        //5.获取当前系统的名称
        String osName = environment.getProperty("os.name");
        osName="os.Linux";
        //6.判断是否包含windos规则
        if(osName.contains("Windows")){
            //需要注册到ioc容器中
            return true;
        }
        //不需要注册到Ioc容器中
        return false;
    }
}
