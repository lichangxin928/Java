package com.typefilter;

import com.itheima.annotations.District;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.util.PathMatcher;

import java.util.Properties;

/**
 * 自定义扫描规则过滤器
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class DistrictTypeFilter extends AbstractTypeHierarchyTraversingFilter {

    //定义路径校验的对象
    private PathMatcher pathMatcher;

    //定义区域名称
    //注意：此处的数据，应该是读取配置文件获取的。
    //此处不能使用@Value注解读取properties配置文件的内容。
    //因为负责填充属性值的InstantiationAwareBeanPostProcessor与TypeFilter实例创建根本不搭边。
    private String districtName;


    public DistrictTypeFilter(){
        /**
         * 调用父类的构造函数
         * 第一个参数：不考虑基类
         * 第二个参数：不考虑接口上的信息
         */
        super(false,false);

        //借助Spring默认的Resource通配符路径方式
        pathMatcher = new AntPathMatcher();

        //读取配置文件（硬编码）
        try{
            Properties properties = PropertiesLoaderUtils.loadAllProperties("district.properties");
            //给distirctName赋值
            districtName = properties.getProperty("common.district.name");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 本类将注册为Exclude，返回true表示拒绝
     * @param className
     * @return
     */
    @Override
    protected boolean matchClassName(String className) {
        try {
            /**
             * 判断是否在指定包下的类（只处理和区域相关的业务类）
             */
            if (!isPotentialPackageClass(className)) {
                //不符合路径规则
                return false;
            }
            //判断当前区域和配置的区域是否一致，不一致则不能注册到Spring的Ioc容器之中
            Class<?> clazz = ClassUtils.forName(className, DistrictTypeFilter.class.getClassLoader());
            //获取District注解
            District district = clazz.getAnnotation(District.class);
            //判断是否有此注解
            if(district == null){
                return false;
            }
            //取出注解的属性
            String districtValue = district.value();
            //校验，如果取出的value属性的值和配置文件中提供值一致，则注册到ioc容器中，返回true。否则返回false。
            return (!districtName.equalsIgnoreCase(districtValue));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //定义可以处理类的类名，指定的package下的
    private static final String PATTERN_STANDARD = ClassUtils.convertClassNameToResourcePath("com.itheima.service.*.*");

    /**
     * 本类逻辑中可以处理的类
     * @param className
     * @return
     */
    private boolean isPotentialPackageClass(String className){
        //1.将类名转换成为资源路径，以匹配是否符合扫描条件
        String path = ClassUtils.convertClassNameToResourcePath(className);
        //2.校验
        return pathMatcher.match(PATTERN_STANDARD,path);
    }
}
