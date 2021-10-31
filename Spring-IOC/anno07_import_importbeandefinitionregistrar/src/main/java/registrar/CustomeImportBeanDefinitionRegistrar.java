package registrar;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AspectJTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 自定义bean定义注册器
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class CustomeImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    //定义表达式
    private  String expression;

    //使用者自定义的扫描包
    private String customePackage;

    /**
     * 默认构造函数
     * 用于给表达式赋值
     */
    public CustomeImportBeanDefinitionRegistrar(){
        try{
            //1.读取properties文件，创建Properties对象
            Properties properties = PropertiesLoaderUtils.loadAllProperties("customeimport.properties");
            //2.给变量赋值
            expression = properties.getProperty("custome.importselector.expression");
            customePackage = properties.getProperty("custome.importselect.package");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 实现注册bean的功能（它是通过扫描指定包实现的）
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //1.定义扫描包的集合
        List<String> basePackages = null;
        //2.判断是否有@ComponentScan注解
        if(importingClassMetadata.hasAnnotation(ComponentScan.class.getName())){
            //3.取出注解的属性
            Map<String,Object> attributes = importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName());
            //4.获取属性为basePackages(或者是value)
            basePackages = new ArrayList<>(Arrays.asList((String[]) attributes.get("basePackages")));
        }
        //5.判断是否有此注解，如果没有此注解时，List集合是null。如果有此注解，但是没有指定basePackages属性或者value属性是,List集合size是0
        if(basePackages == null || basePackages.size() == 0){
            String basePackage = null;//用于记录@Import注解出现类，这个类所在的包
            try{
                //6.取出@Import注解的类所在的包
                basePackage = Class.forName(importingClassMetadata.getClassName()).getPackage().getName();
            }catch (Exception e){
                e.printStackTrace();
            }
            //7.添加到扫描包的集合中
            basePackages = new ArrayList<>();
            basePackages.add(basePackage);
        }

        //判断用户是否配置了扫描的包
        if(!StringUtils.isEmpty(customePackage)){
            basePackages.add(customePackage);
        }


        //8.创建类路径扫描器
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry,false);
        //9.创建类型过滤器
        TypeFilter typeFilter  = new AspectJTypeFilter(expression,CustomeImportBeanDefinitionRegistrar.class.getClassLoader());
        //11.把类型过滤器添加到扫描器中
        scanner.addIncludeFilter(typeFilter);
        //12.扫描指定的包
        scanner.scan(basePackages.toArray(new String[basePackages.size()]));
    }
}
