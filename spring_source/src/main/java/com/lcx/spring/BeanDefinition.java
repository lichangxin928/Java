package com.lcx.spring;

public class BeanDefinition {

    private Class beanClass;
    private String scope;
    private Boolean isLazy;

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Boolean getLazy() {
        return isLazy;
    }

    public void setLazy(Boolean lazy) {
        isLazy = lazy;
    }

    public BeanDefinition() {
    }

    public BeanDefinition(Class beanClass, String scope, Boolean isLazy) {
        this.beanClass = beanClass;
        this.scope = scope;
        this.isLazy = isLazy;
    }
}
