//package cn.itcast.shiro.config;
//
//import cn.itcast.shiro.realm.CustomRealm;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfiguration {
//
//    // 1. 创建 Realm
//    @Bean
//    public CustomRealm getRealm(){
//        return new CustomRealm();
//    }
//    // 2. 创建安全管理器
//    //配置安全管理器
//    @Bean
//    public SecurityManager securityManager(CustomRealm realm) {
//        //使用默认的安全管理器
//        SecurityManager securityManager = new
//                DefaultWebSecurityManager(realm);
//        //将自定义的realm交给安全管理器统一调度管理
//        return securityManager;
//    }
//
//    // 3. 配置 shiro 的过滤工厂
//    //Filter工厂，设置对应的过滤条件和跳转条件
//    @Bean
//    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
//        //1.创建shiro过滤器工厂
//        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
//        //2.设置安全管理器
//        filterFactory.setSecurityManager(securityManager);
//        //3.通用配置（配置登录页面，登录成功页面，验证未成功页面）
//        filterFactory.setLoginUrl("/autherror?code=1"); //设置登录页面
//        filterFactory.setUnauthorizedUrl("/autherror?code=2"); //授权失败跳转页面
//        //4.配置过滤器集合
//        /**
//         * key ：访问连接
//         *     支持通配符的形式
//         * value：过滤器类型
//         *     shiro常用过滤器
//         *         anno   ：匿名访问（表明此链接所有人可以访问）
//         *         authc   ：认证后访问（表明此链接需登录认证成功之后可以访问）
//         */
//        Map<String,String> filterMap = new LinkedHashMap<String,String>();
//        // 配置不会被拦截的链接 顺序判断
//        filterMap.put("/user/home", "anon");
//        filterMap.put("/user/**", "authc");
//        //5.设置过滤器
//        filterFactory.setFilterChainDefinitionMap(filterMap);
//        return filterFactory;
//    }
//    //配置shiro注解支持
//    @Bean
//    public AuthorizationAttributeSourceAdvisor
//    authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new
//                AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
//
//}
