package com.lcx.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LcxApplicationContext {

    private Class configClass;
    // 需要实例化的class对象
    private List<Class> classList;
    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();   // BeanDefinition
    private Map<String,Object> singletonObjects = new ConcurrentHashMap<>();    // 单例池
    private List<BeanPostProcessor>  beanPostProcessorList = new LinkedList();

    public LcxApplicationContext(Class configClass){
        this.configClass = configClass;
        classList = new LinkedList<>();

        // 扫描
        scan(configClass);
        // 解析
        initBeanDefinition();
        // 基于class 去创建
        instanceSingletonBean();
    }


    /**
     * 扫描路径，根据 ComponentScan 中传入的路径，进行扫描，如果被component修饰，就将需要管理的bean对象的class 对象存到 classList 中去
     * @param configClass
     */
    private void scan(Class configClass) {
        // 扫描
        ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String path = annotation.value();
        path = path.replace(".","/");

        ClassLoader loader = LcxApplicationContext.class.getClassLoader();
        URL url = loader.getResource(path);
        File file = new File(url.getFile());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f:files){
                String absolutePath = f.getAbsolutePath();
                absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                absolutePath = absolutePath.replace("\\",".");
                System.out.println(absolutePath);
                try {
                    Class<?> aClass = loader.loadClass(absolutePath);
                    if(aClass.isAnnotationPresent(Component.class))
                        classList.add(aClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过扫描过后，对List<Class> 中的对象进行解析，对该类中使用的注解进行解析
     */
    private void initBeanDefinition() {
        for(Class clazz:classList){

            if(BeanPostProcessor.class.isAssignableFrom(clazz)){
                try {
                    BeanPostProcessor instance = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
                    beanPostProcessorList.add(instance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            BeanDefinition beanDefinition = new BeanDefinition();

            Component component = (Component) clazz.getAnnotation(Component.class);
            String beanName = component.value();

            if(clazz.isAnnotationPresent(Scope.class)){
                Scope scopeAnnotation = (Scope) clazz.getAnnotation(Scope.class);
                beanDefinition.setScope(scopeAnnotation.value());
            } else {
                beanDefinition.setScope("singleton");
            }
            // Lazy 类似
            beanDefinition.setBeanClass(clazz);
            beanDefinitionMap.put(beanName,beanDefinition);
        }
    }

    /**
     * 如果是单例，就进行实例化，初始化单例池
     */
    private void instanceSingletonBean(){
        for (String beanName:beanDefinitionMap.keySet()){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            // 如果是单例的,就尝试拿，如果没有拿到，就尝试创建
            if(beanDefinition.getScope().equals("singleton")){
                if(!singletonObjects.containsKey(beanName)){
                    Object bean = doCreateBean(beanName,beanDefinition);
                    singletonObjects.put(beanName,bean);
                }

            }
        }
    }

    /**
     * 开始创建Bean对象
     * @param beanName bean 名字
     * @param beanDefinition
     * @return
     */
    private Object doCreateBean(String beanName, BeanDefinition beanDefinition) {

        Class beanClass = beanDefinition.getBeanClass();
        Object o = null;
        try {
            // 1. 实例化
            o = beanClass.getDeclaredConstructor().newInstance();
            // 2. 属性填充
            Field[] declaredFields = beanClass.getDeclaredFields();
            for(Field field:declaredFields){
                if(field.isAnnotationPresent(Autowired.class)){
                    Object bean = getBean(field.getName());
                    field.setAccessible(true);
                    field.set(o,bean);
                }
            }
            // aware
            if(o instanceof BeanNameAware){
                BeanNameAware aware = (BeanNameAware) o;
                ((BeanNameAware) o).setBeanName(beanName);
            }
            // 初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                o = beanPostProcessor.postProcessBeforeInitialization(o,beanName);
            }
            // 初始化
            if(o instanceof InitializingBean){
                ((InitializingBean)o).afterPropertiesSet();
            }
            // 初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                o = beanPostProcessor.postProcessAfterInitialization(o,beanName);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return o;
    }
    public Object getBean(String name){
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition.getScope().equals("prototype")){
            // 创建 bean
            Object bean = doCreateBean(name,beanDefinition);
            return bean;
        }else if(beanDefinition.getScope().equals("singleton")){
            // 单例池中找
            Object bean =  singletonObjects.get(name);
            if(bean == null){
                Object bean1 = doCreateBean(name, beanDefinition);
                singletonObjects.put(name,bean1);
                return bean1;
            }
            return bean;
        }
        return null;
    }

}
