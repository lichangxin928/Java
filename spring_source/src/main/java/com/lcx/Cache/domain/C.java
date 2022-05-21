package com.lcx.Cache.domain;

import com.lcx.spring.BeanPostProcessor;
import com.lcx.spring.Component;
import org.springframework.beans.factory.FactoryBean;

@Component
public class C implements FactoryBean, BeanPostProcessor {
    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
