package com.itheima.customer;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.util.Map;
import java.util.Set;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class CustomeBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        //定义Bean的名称
        String beanName = null;
        //1.判断当前bean的定义信息是否是注解的
        if(definition instanceof AnnotatedBeanDefinition){
           //2.把definition转成注解的bean定义信息
           AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition)definition;
           //2.获取注解Bean定义的元信息
           AnnotationMetadata annotationMetadata = annotatedBeanDefinition.getMetadata();
           //3.获取定义信息中的所有注解
           Set<String> types = annotationMetadata.getAnnotationTypes();
           //4.遍历types集合
           for(String type : types){
               //5.得到注解的属性
               AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(type, false));
               //6.判断attributes是否为null，同时必须是@Component及其衍生注解
               if (attributes != null && isStereotypeWithNameValue(type, annotationMetadata.getMetaAnnotationTypes(type), attributes)) {
                   //7.获取value属性的值
                   Object value = attributes.get("value");
                   //8.判断value属性是否为String类型
                   if (value instanceof String) {
                       String strVal = (String) value;
                       //9.判断value属性是否有值
                       if (StringUtils.hasLength(strVal)) {
                           if (beanName != null && !strVal.equals(beanName)) {
                               throw new IllegalStateException("Stereotype annotations suggest inconsistent " +
                                       "component names: '" + beanName + "' versus '" + strVal + "'");
                           }
                           beanName = strVal;
                       }
                   }
               }
           }
        }
        return beanName != null ? "my"+beanName : "my"+buildDefaultBeanName(definition);
    }

    private static final String COMPONENT_ANNOTATION_CLASSNAME = "org.springframework.stereotype.Component";

    private boolean isStereotypeWithNameValue(String annotationType,
                                                Set<String> metaAnnotationTypes, @Nullable Map<String, Object> attributes) {

        boolean isStereotype = annotationType.equals(COMPONENT_ANNOTATION_CLASSNAME) ||
                metaAnnotationTypes.contains(COMPONENT_ANNOTATION_CLASSNAME) ||
                annotationType.equals("javax.annotation.ManagedBean") ||
                annotationType.equals("javax.inject.Named");

        return (isStereotype && attributes != null && attributes.containsKey("value"));
    }

    private String buildDefaultBeanName(BeanDefinition definition) {
        String beanClassName = definition.getBeanClassName();
        Assert.state(beanClassName != null, "No bean class name set");
        String shortClassName = ClassUtils.getShortName(beanClassName);
        return Introspector.decapitalize(shortClassName);
    }
}
