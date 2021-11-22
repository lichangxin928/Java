# 快速上手 SpringBoot 

## 一、SpringBoot 概述

SpringBoot 是由 Pivotal 团队提供的全新框架，其设计目的是用来简化 Spring 应用的初始搭建以及开发过程
    
- Spring 程序缺点

      1. 依赖设置繁琐
      2. 配置繁琐

- SpringBoot 程序优点
  
      1. 起步依赖（简化依赖配置）
      2. 自动配置（简化常用工程相关配置）
      3. 辅助功能（内置服务器...）

### 1.1 SpringBoot 自动帮助我们管理各个依赖的版本

    1. 开发SpringBoot 程序要继承 spring-boot-starter-parent
    2. spring-boot-starter-parent中定义了若干个依赖管理
    3. spring-boot-starter-parent继承了 spring-boot-dependencies，其中定义了依赖的版本号
    4. 继承parent 模块可以避免多个依赖使用相同技术时出现依赖版本冲突
    5. 继承parent的形式也可以采用引入依赖的形式实现效果

### 1.2 starter

    SpringBoot 中常见项目名称，定义了当前项目使用的所有依赖坐标，以达到减少依赖配置的目的

### 1.3 parent

    所有SpringBoot 项目要继承的项目，定义了若干个坐标版本号，以达到减少依赖冲突的目的
    spring-boot-starter-parent 各版本间存在着诸多坐标版本不同

### 1.4 实际开发

    使用任意坐标时，仅书写GAV 中的 G和A V由SpringBoot来提供，除非 SpringBoot 中没有对应的 V
    如果发生坐标错误，再指定 Version （小心版本冲突）

### 1.5 引导类

1. 启动方式：
```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
2. SpringBoot的引导类是Boot工程的执行入口，，运行 main 方法就可以启动项目
3. SpringBoot工程运行后初始化Spring容器，扫描引导类所在包加载bean


### 1.6 辅助功能

    1. 内嵌Tomcat服务器是 SpringBoot辅助功能之一
    2. 内嵌Tomcat工作原理是将Tomcat服务器作为对象运行，并将该对象交给Spring容器
    3. 变更内嵌服务器的思想是去除现有服务器，添加全新的服务器
## 二、基础配置

### 2.1 属性配置方式

**属性配置**
SpringBoot默认配置文件 Application.properties，通过键值对配置对应属性
1. 修改服务器端口
```properties
server.port=80
```
2. 修改 banner 
```properties
 spring.main.banner-mode=off
 spring.banner.image.location=img.png
```
3. 日志的配置
```properties
loging.level.root=debug
```

**配置文件类型**
    
    properties 格式
    yml 格式（主流）
    yaml 格式

**加载优先级**

    配置相同
    properties > yml > yaml
    不同配置会全留下来

### 2.2 YAML 格式
- YAML（Ain't Markup Language）一种数据序列化格式
- 优点
  - 容易阅读
  - 容易与脚本语言交互
  - 以数据为中心
- YAML文件扩展名
  - .yml（主流）
  - .yaml

**语法规则**

- 大小写敏感
- 属性层级关系使用多行描述，每行结尾使用冒号结束
- 使用缩进表示层级关系，同层级左侧对齐，只允许使用空格（不允许使用Tab键）
- 属性值前面添加空格
- \# 来表示注释

**读取 yaml 格式数据**

通过 @Value 注解来读取数据
```java
    // ${一级属性.二级属性....} 遇到数组用中括号
    @Value("${contry}")
    private String contry;
```

**yaml文件中的变量引用**
```yaml
# 使用 ${属性名}引用数据，需要使用转义字符就使用引号包裹
```

**读取 yaml文件中的全部属性**

```java
import org.springframework.beans.factory.annotation.Autowired;

@Autowired
private Environment env;
```
使用自动装配将所有数据装配到对象中，通过 getProperty 来获取

**局部封装成对象**

1. 使用 @ConfigurationProperties() 这个注解绑定配置信息到封装类中
2. 封装类需要定义为Spring 管理的Bean，否则无法进行属性注入
**示例**
```yaml
datasource:
  driver: com.mysql.jdbc.Driver
  url: jdbc:mysql://localhost/test
  username: root
  password: 123
```

```java
package com.lcx.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("datasource")
public class MyDataSource {
    private String driver;
    private String url;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "MyDataSource{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

```

## 三、整合第三方技术
### 3.1 整合 Junit 
> 创建模块自动会导入 Junit 的相关依赖，但是要注意的是，当测试类文件和
> 程序入口类不在同一个包目录下时，需要利用注解来指明

### 3.2 整合 mybatis

**首先要知道要有哪些信息**

- 核心配置：数据库相关的连接信息
- 映射配置：SQL 映射

导入步骤：
1. 导入对应的 starter（mybatis，sql driver）
2. 数据库相关信息转换成配置
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost/test?serverTimezone=UTC
    username: root
    password: 123

```
3. 数据库 sql 映射需要添加 @Mapper 被容器识别到
```java
@Mapper
public interface StudentDao {

    @Select("select * from student where id = #{id}")
    Student findById(Integer id);
}

```

**常见问题**
1. MySQL 8.X 驱动要求强制设置时区
2. 驱动类过时，提醒更换为 com.mysql.cj.jdbc.Driver

### 3.3 整合 mybatis-plus

**mybatis-plus 和 mybatis 的区别**
- 导入的坐标不同
- 数据层实现简化

1. 导入坐标
```xml
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3</version>
        </dependency>
```

2. dao 层接口继承 BaseMapper<>
```java
@Mapper
public interface StudentDao extends BaseMapper<Student> {
}

```
3. 测试类进行使用
```java
    @Test
    void contextLoads() {
        Student student = studentDao.selectById(8);
        System.out.println(student);
    }
```
### 3.4 整合 Druid

1. 导入依赖
```xml
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>druid-spring-boot-starter</artifactId>
  <version>1.2.8</version>
</dependency> 
```
2. 写入配置（两种方式）
```yaml
 #spring:
 #  datasource:
 #    driver-class-name: com.mysql.cj.jdbc.Driver
 #    url: jdbc:mysql://localhost/test?serverTimezone=UTC
 #    username: root
 #    password: 123
 #    type: com.alibaba.druid.pool.DruidDataSource

 spring:
   datasource:
     druid:
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost/test?serverTimezone=UTC
       username: root
       password: 123

```

3. 直接在测试类中使用

### 3.5 整合第三方技术总结

**整合任意第三方技术**
- 导入对应的 starter
- 配置对应的设置或采用默认配置

## 四、SSM整合案例(crud)

1. pom.xml：配置起步依赖
2. application.yml：设置数据源、端口、框架技术相关配置
3. dao：基础BaseMapper、设置@Mapper
4. dao测试类
5. service：调用数据层接口或 MyBatis-plus 提供的接口快速开发
6. service 测试类
7. controller：基于Restful开发，使用Postman测试跑通功能
8. 页面：放置在 resources 目录下的static目录中

# 五、Web 开发

## 5.1 简单功能分析
### 5.1.1 静态资源访问
#### **1. 静态资源目录**

    只要放在类路径下：called /static(or /public or /resources or /META-INF/resources)
    访问当前项目路径/ + 静态资源名，只需要写静态资源名就能访问，springboot

原理：/**
收到请求后先去匹配 controller，如果不能处理就去交给静态资源处理器。

**改变默认的静态资源位置**
```yaml
  web:
    resources:
      static-locations: classpath:/path
```

#### **2. 静态资源访问前缀**
```yaml
spring:
  mvc:
    static-path-pattern: /res/**
```
当前项目 + static-path-pattern + 静态资源名 = 静态资源文件夹下的

### 5.1.2 欢迎页支持
#### **1. 自动加载的欢迎页面**
1. 在静态资料路径下 index.html
   1. 可以配置静态资源路径
   2. 但是不可以配置静态资源的访问前缀。否则导致index.html 不能被默认访问
2. controller 能够处理 /index 请求的controller 自动会请求

#### **2. favicon支持**
将 favicon.ico 放置在静态资源路径下，会自动将favicon.ico 作为页面小图标

### 5.1.3 静态资源配置原理
#### 加载配置类源码
1. springboot启动默认加载 XXXAutoConfiguration类（自动配置类）
2. SpringMVC功能的自动配置类是 WebMvcAutoConfiguration
```java
@Configuration(
    proxyBeanMethods = false
)
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
@AutoConfigureOrder(-2147483638)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
public class WebMvcAutoConfiguration {}
```
#### 配置类生效源码
```java
@Configuration(
        proxyBeanMethods = false
    )
    @Import({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class})
    @EnableConfigurationProperties({WebMvcProperties.class, ResourceProperties.class, WebProperties.class})
    @Order(0)
    public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer, ServletContextAware {}
```
配置文件的相关属性和什么进行了绑定

如果配置中只有一个有参构造器，那么属性会自动从容器中找
```java
// ResourceProperties 获取 spring.resources
// WebMvcProperties 获取 sprint.mvc
// ListableBeanFactory 获取bean 工厂
// ResourceHandlerRegistrationCustomizer 获取资源处理自定义器
// ...
        public WebMvcAutoConfigurationAdapter(ResourceProperties resourceProperties, WebProperties webProperties, WebMvcProperties mvcProperties, ListableBeanFactory beanFactory, ObjectProvider<HttpMessageConverters> messageConvertersProvider, ObjectProvider<WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider, ObjectProvider<DispatcherServletPath> dispatcherServletPath, ObjectProvider<ServletRegistrationBean<?>> servletRegistrations) {
            this.resourceProperties = (Resources)(resourceProperties.hasBeenCustomized() ? resourceProperties : webProperties.getResources());
            this.mvcProperties = mvcProperties;
            this.beanFactory = beanFactory;
            this.messageConvertersProvider = messageConvertersProvider;
            this.resourceHandlerRegistrationCustomizer = (WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer)resourceHandlerRegistrationCustomizerProvider.getIfAvailable();
            this.dispatcherServletPath = dispatcherServletPath;
            this.servletRegistrations = servletRegistrations;
            this.mvcProperties.checkConfiguration();
        }
```

#### **资源处理的默认规则源码**
```java
public void addResourceHandlers(ResourceHandlerRegistry registry) {
            if (!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
            } else {
                Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
                CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
                if (!registry.hasMappingForPattern("/webjars/**")) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

                String staticPathPattern = this.mvcProperties.getStaticPathPattern();
                if (!registry.hasMappingForPattern(staticPathPattern)) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

            }
        }
```

#### 可以直接禁用所有静态资源
```yaml
spring:
  web:
    resources:
      add-mappings: false
```

#### **默认资源配置源码**
```java
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[]{"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
    private String[] staticLocations;
    private boolean addMappings;
    private final ResourceProperties.Chain chain;
    private final ResourceProperties.Cache cache;
```

#### **欢迎页源码**
```java
@Bean
public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
    WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext), applicationContext, this.getWelcomePage(), this.mvcProperties.getStaticPathPattern());
    welcomePageHandlerMapping.setInterceptors(this.getInterceptors(mvcConversionService, mvcResourceUrlProvider));
    welcomePageHandlerMapping.setCorsConfigurations(this.getCorsConfigurations());
    return welcomePageHandlerMapping;
}

WelcomePageHandlerMapping(TemplateAvailabilityProviders templateAvailabilityProviders, ApplicationContext applicationContext, Resource welcomePage, String staticPathPattern) {
    if (welcomePage != null && "/**".equals(staticPathPattern)) {
        logger.info("Adding welcome page: " + welcomePage);
        this.setRootViewName("forward:index.html");
    } 
    else if (this.welcomeTemplateExists(templateAvailabilityProviders, applicationContext)) {
        logger.info("Adding welcome page template: index");
        this.setRootViewName("index");
    }

}

```


### 5.2.1 请求参数处理
#### 请求映射
1. xxxMapping
2. Rest 风格（使用Http请求的方式动词来表示对资源的操作）
   1. 以前：/getUser 获取用户  /deleteUser 删除用户 /editUser 修改用户 /saveUser 保存用户
   2. 现在：/user get获取     Delete 删除用户      PUT 修改用户    POST 保存用户
3. 核心 Filter HiddenHttpMethodFilter

开启这个过滤器
```yaml
  mvc:
    hiddenmethod:
      filter:
        enabled: true
```
Rest原理
1. 表单提交的时候会带上_method=PUT
2. 请求过来呗HidderHttpMethodFilter拦截
   1. 请求是否正常，并且判断是否是 POST
      1. 获取到_method 属性的值
      2. 兼容以下请求，PUT DELETE PATCH
      3. 原生request包装模式 requestWrapper重写了getMethod 方法
      4. 过滤器使用 wrapper。以后的方法调用getMethod是调用requestWrapper的

```java
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest requestToUse = request;
        if ("POST".equals(request.getMethod()) && request.getAttribute("javax.servlet.error.exception") == null) {
            String paramValue = request.getParameter(this.methodParam);
            if (StringUtils.hasLength(paramValue)) {
                String method = paramValue.toUpperCase(Locale.ENGLISH);
                if (ALLOWED_METHODS.contains(method)) {
                    requestToUse = new HiddenHttpMethodFilter.HttpMethodRequestWrapper(request, method);
                }
            }
        }
```

### 5.2.2 请求映射处理

> 继承树结构：DispatcherServlet 继承 FrameworkServlet 继承 HttpServletBean 继承 HttpServlet

**FrameworkServlet中重写的doGet和doPost方法等等**
```java
    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected final void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected final void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

```

**进入到 processRequest方法中**
```java
protected final void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 初始化的过程
        long startTime = System.currentTimeMillis();
        Throwable failureCause = null;
        LocaleContext previousLocaleContext = LocaleContextHolder.getLocaleContext();
        LocaleContext localeContext = this.buildLocaleContext(request);
        RequestAttributes previousAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes requestAttributes = this.buildRequestAttributes(request, response, previousAttributes);
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
        asyncManager.registerCallableInterceptor(FrameworkServlet.class.getName(), new FrameworkServlet.RequestBindingInterceptor());
        this.initContextHolders(request, localeContext, requestAttributes);

        try {
            // 执行核心方法 doService
            this.doService(request, response);
        } catch (IOException | ServletException var16) {
            failureCause = var16;
            throw var16;
        } catch (Throwable var17) {
            failureCause = var17;
            throw new NestedServletException("Request processing failed", var17);
        } finally {
            this.resetContextHolders(request, previousLocaleContext, previousAttributes);
            if (requestAttributes != null) {
                requestAttributes.requestCompleted();
            }

            this.logResult(request, response, (Throwable)failureCause, asyncManager);
            this.publishRequestHandledEvent(request, response, startTime, (Throwable)failureCause);
        }

    }
```

**查看这个doService方法，发现是一个抽血方法， 下层来实现的**
```java
protected abstract void doService(HttpServletRequest var1, HttpServletResponse var2) throws Exception;
```

**于是我们来到了DispatcherServlet这个类中，果然发现了doService的实现方法**

```java
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.logRequest(request);
        Map<String, Object> attributesSnapshot = null;
        if (WebUtils.isIncludeRequest(request)) {
            attributesSnapshot = new HashMap();
            Enumeration attrNames = request.getAttributeNames();

            label95:
            while(true) {
                String attrName;
                do {
                    if (!attrNames.hasMoreElements()) {
                        break label95;
                    }

                    attrName = (String)attrNames.nextElement();
                } while(!this.cleanupAfterInclude && !attrName.startsWith("org.springframework.web.servlet"));

                attributesSnapshot.put(attrName, request.getAttribute(attrName));
            }
        }

        request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.getWebApplicationContext());
        request.setAttribute(LOCALE_RESOLVER_ATTRIBUTE, this.localeResolver);
        request.setAttribute(THEME_RESOLVER_ATTRIBUTE, this.themeResolver);
        request.setAttribute(THEME_SOURCE_ATTRIBUTE, this.getThemeSource());
        if (this.flashMapManager != null) {
            FlashMap inputFlashMap = this.flashMapManager.retrieveAndUpdate(request, response);
            if (inputFlashMap != null) {
                request.setAttribute(INPUT_FLASH_MAP_ATTRIBUTE, Collections.unmodifiableMap(inputFlashMap));
            }

            request.setAttribute(OUTPUT_FLASH_MAP_ATTRIBUTE, new FlashMap());
            request.setAttribute(FLASH_MAP_MANAGER_ATTRIBUTE, this.flashMapManager);
        }

        // 上面的代码都是在进行初始化操作
        
        try {
            // 最终执行了这个方法
            this.doDispatch(request, response);
        } finally {
            if (!WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted() && attributesSnapshot != null) {
                this.restoreAttributesAfterInclude(request, attributesSnapshot);
            }

        }

    }
```

**通过DispatcherServlet中重写的doService方法，我们可以看出是调用了doDispatch这个方法**

**每一个请求进来都会调动这个方法**

```java
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 封装一下Request请求
        HttpServletRequest processedRequest = request;
        // 初始化HandlerExecutionChain
        HandlerExecutionChain mappedHandler = null;
        boolean multipartRequestParsed = false;

        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

        try {
            ModelAndView mv = null;
            Exception dispatchException = null;

            try {
                // 检查是否文件上传
                processedRequest = checkMultipart(request);
                multipartRequestParsed = (processedRequest != request);
                // 寻找到Request对应哪一个Hander（controller）方法
                mappedHandler = getHandler(processedRequest);
                if (mappedHandler == null) {
                    noHandlerFound(processedRequest, response);
                    return;
                }

                // Determine handler adapter for the current request.
                HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

                // Process last-modified header, if supported by the handler.
                String method = request.getMethod();
                boolean isGet = "GET".equals(method);
                if (isGet || "HEAD".equals(method)) {
                    long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                    if (new ServletWebRequest(request, response).checkNotModified(lastModified) && isGet) {
                        return;
                    }
                }

                if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                    return;
                }

                // Actually invoke the handler.
                mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

                if (asyncManager.isConcurrentHandlingStarted()) {
                    return;
                }

                applyDefaultViewName(processedRequest, mv);
                mappedHandler.applyPostHandle(processedRequest, response, mv);
            }
            catch (Exception ex) {
                dispatchException = ex;
            }
            catch (Throwable err) {
                // As of 4.3, we're processing Errors thrown from handler methods as well,
                // making them available for @ExceptionHandler methods and other scenarios.
                dispatchException = new NestedServletException("Handler dispatch failed", err);
            }
            processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
        }
        catch (Exception ex) {
            triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
        }
        catch (Throwable err) {
            triggerAfterCompletion(processedRequest, response, mappedHandler,
                    new NestedServletException("Handler processing failed", err));
        }
        finally {
            if (asyncManager.isConcurrentHandlingStarted()) {
                // Instead of postHandle and afterCompletion
                if (mappedHandler != null) {
                    mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
                }
            }
            else {
                // Clean up any resources used by a multipart request.
                if (multipartRequestParsed) {
                    cleanupMultipart(processedRequest);
                }
            }
        }
    }
```

**寻找handlerMappings的方法**

```java
	@Nullable
	protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
		if (this.handlerMappings != null) {
			for (HandlerMapping mapping : this.handlerMappings) {
				HandlerExecutionChain handler = mapping.getHandler(request);
				if (handler != null) {
					return handler;
				}
			}
		}
		return null;
	}
```
**通过调试发现此方法中的handlerMappings里面就包括了所有的controller，并且还有一个默认的欢迎页，WelcomePage的handler**
**这也解释了默认情况下的WelcomePage**


**所有的请求映射都在HandlerMapping中**

- springboot自动配置欢迎页的HandlerMapping，访问/能访问到index.html
- springboot自动配置了RequestMappingHandlerMapping
- 请求进来，挨个尝试所有的HandlerMapping看是否有请求
  - 如果有，就找到这个请求的Hander
  - 如果没有，就继续找
- 需要一些自定义的映射处理，我们也可以自己给容器中放HandlerMapping

