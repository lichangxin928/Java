## 1. 概述    
    1. SpringMVC:是基于spring 的一个框架，实际上就是 spring 的一个模块，专门是做web开发的，servlet 的一个升级版
        web 开发底层是servlet，框架是在servlet基础上面加入一些功能，让你做web开发
    
    2. SpringMVC就是一个spring 容器，spring 是容器，ioc 能够管理对象，使用 <bean> @Component @Repository @Service @Controler
        SpringMVC 能够创建对象，放入到容器中（SpringMVC 容器），springmvc容器中放的是控制器对象。
    
        我们要做的就是 使用 @Controller 创建控制器对象，把对象放入到 springmvc 容器中，把创建的对象作为控制器使用，这个控制器对象能
        接受用户的请求， 显示处理结果，就当做是一个servlet 来使用
    
        使用@Cotroller注解创建的是一个普通类的对象，不是servlet。springmvc 赋予了控制器对象一下额外的功能
    
        web开发底层是 servlet，springmvc中有一个对象是 servlet ：DispatchServlet(中央调度器)
        DispatchServlet：负责接受用户的所有请求，用户吧请求给 DispatchServlet，之后就是 DispatchServlet 把请求
        转发给我们的Controller 对象，最后是Controller 对象处理请求
    
    
    3. 过程：index.jsp -----DispatchServlet--- 转发，分配给 ------- Controller 对象，做出回复

    4. 优点：
        1. 基于 MVC 架构
        2. 容易理解，上手快，使用简单
        3. 作为spring 的一部分，能够使用 Spring 的 IOC 和 AOP 。方便整合
        4. SpringMVC 强化注解的使用，在控制器 Service Dao 都可以使用。方便灵活
            使用 @Controller 创建处理器对象，@Service 创建业务对象，@Autowried 或者 @Resource 在控制器中注入 Service
## 2. 第一个springMVC 项目
>需求：用户在页面发送一个请求，请求给 springMVC 的控制器对象
> 并显示请求的处理结果

**实现步骤**

1. 新建 web maven 工程
2. 加入依赖
    spring—webmvc依赖，间接吧sping 依赖加入到项目
3. 重点：在web.xml 中注册springMVC 框架的核心对象 DispatcherServlet
    1. DispatcherServlet叫做中央调度器，是一个Servlet，它的父类是HTTPServlet
    2. DispatcherServlet也叫做前端控制器（front Controller）
    3. DispatcherServlet负责接受用提交的请求，调用其他的控制器对象，并把请求的处理结果显示给用户
4. 创建一个发起请求是页面， index.jsp
5. 创建控制器类
   1. 在类的上面加入@Controller注解，创建对象，并放入到springMVC容器中
   2. 在类的方法中加上@RequestMapping 的注解
6. 创建一个座位结果的jsp，显示请求处理的结果
7. 创建springMVC 的配置文件
    1. 声明组件扫描器，指定 @Controller 注解所在的包名
    2. 声明视图解析器，帮助处理视图

---
### 3. springmvc 执行过程源码分析：
   1. tomcat 启动，创建容器的过程
      通过load-on-start 标签指定的1，创建 DispatcherServlet对象，
      DispatcherServlet 的父类是 HttpServlet ，是一个 Servlet 在被创建时执行 init() 方法
      在init() 方法中：
      // 创建容器，读取配置文件
      WebApplicationContext ctx = new ClassPathXmlApplication("springmvc.xml");
      // 把容器放入到 ServletContext中
      getServletContext().setAttribute(key,ctx)

   上面创建容器的作用：创建 @Controller 注解所在的类对象，创建MyController对象，
   这个对象放入到 springmvc 的容器中，容器是 map，类似 map.put("myController",MyController对象)
   
   2. 请求的处理过程
      执行 Servlet 中的 Service方法
```java
        protected void service(HttpServletRequest request,HttpServletResponse response)
        
        protected void doService(HttpServletRequest request,HttpServletResponse response)

        this.doDispatch(request,response){
            // 调用MyController 的 doSome() 方法
        }
```
### 示例代码

**Web.xml 配置文件**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
<!--    声明。注册 springmvc 的核心对象DispatcherServlet
        需要在服务器启动后，创建DispatcherServlet对象的实例
        为什么：
            因为 DispatcherServlet 在他创建的过程中，会同时创建 springmvc 容器对象
            读取springmvc的配置文件，吧这个配置文件的对象都创建好，当用户发起请求时就可以直接
            使用对象了

        Servlet 初始化会执行 init() 方法。DispatcherServlet 在inti() 中
        创建容器，读取配置文件，把容器对象放入到 ServletContext
        webApplicationContext ctx = ClassPathXmlApplication("Spring.xml");

        // 把容器放入 ServletContext 中
        getServletContext().setAttribute(key,ctx);
-->
    <servlet>
        <servlet-name>myweb</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

<!--        自定义在mvc 中读取文件的规则-->
        <init-param>
<!--            自定义springmvc 的配置文件的属性-->
            <param-name>contextConfigLocation</param-name>
<!--            指定自定义文件的路径-->
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
<!--        在 tomcat 启动后创建 Servlet 对象-->
<!--        表示tomcat启动后创建对象的顺序，他的值是整数，数值越小创建对象的时间越早-->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>myweb</servlet-name>
<!--
    使用框架的时候，url-pattern可以使用两种值
    1. 使用扩展名方式，语法：*.xxx,xxxx是自定义的扩展名。常用方式 *.do,*.action *.mvc等
        http://localhost:8080/myweb/some.do 。。。
    2. 使用斜杠
-->
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>
</web-app>
```
        
**springmvc.xml 配置文件**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
<!--    声明组件扫描器-->
    <context:component-scan base-package="com.lcx.controller"/>
<!--    视图解析器，帮助开发人员设置视图文件的路径-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
<!--        前缀：视图文件的路径-->
        <property name="prefix" value="/WEB-INF/view/"/>
<!--        后缀-->
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

**Controller 类**
```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller 创建处理器对象，对象放在springmvc 容器中
 * 位置：在类的上面
 * 和 spring 中是 @Component
 */
@Controller
public class MyController {
    /*
    * 处理用户提交的请求，springmvc 中是使用方法啦处理的
    * 方法是自定义的，可以有多种返回值，多种参数，方法名称自定义
    * */


    /**
     * 准备使用 dosome 来处理 some.do 的请求
     * @RequestMapping:请求映射，作用是把一个请求地址和一个方法绑定在一起，
     *                  一个请求指定要一个方法的处理
     *              属性：1. value 是一个string 表示请求的 URL 地址
     *                  value 必须是唯一的，不能重复。在使用 时推荐以 "/" 开头
     *              位置：1. 在方法上面
     *                   2. 在类的上面
     *          说明：使用 RequestMapping 修饰的方法叫做处理器方法或者控制器方法。
     *          使用 @RequestMapping 修饰的方法可以处理请求的，类似 Servlet 中的 doGet doPost
     *
     * 返回值:ModeLAndView
     *      Model：数据，请求处理完成后要显示给用户的数据
     *      view：视图，比如 jsp 等等。
     */
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(){        // doGet() --- service 请求处理
        //
        ModelAndView mv = new ModelAndView();
        // 添加数据,框架在请求的最后把数据放入到 Request 作用域中
        mv.addObject("msg","Hello World");
        mv.addObject("fun","执行的 doSome 方法");
        //指定视图
        // 框架对视图执行的 forward 操作，Request.getRequestDispatcher(...).forward()
        // mv.setViewName("/show.jsp");
        // 返回 mv
        // 当配置了视图解析器后，可以使用逻辑名称（文件名），指定视图
        // 框架会使用视图解析器的前缀 + 逻辑名称 + 后缀，组成完全路径，这里就是字符连接操作
        mv.setViewName("show");
        return mv;
    }
}

```

**JSP 文件**
```html

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>show.jsp</h1>
    <h2>msg数据:${msg}</h2>
    <h2>fun数据:${fun}</h2>

</body>
</html>

```

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <P>第一个 springmvc 项目</P>
    <p><a href="/SpringMVC_hello_war_exploded/some.do">发起some.do请求</a> </p>
</body>
</html>

```
## 3. 面向注解开发
### 1. @RequestMapping 定义请求规则

    1. @RequestMapping 当使用在类的上方时，表示类中的方法中每一个地址都会添加这个前缀
    2. @RequestMapping 指定特定的请求方式（不写表示都接受） : 请求映射
            属性：method 表示请求的方式，他的值RequestMethod 类枚举
                        RequestMethod.GET;
                        ....
### 处理方法的参数
    处理器方法可以包含以下四类参数，这些参数会在系统调用时由系统自动赋值，可以直接使用
        1. HttpServletRequset
        2. HttpServletResponse
        3. HttpServletSession
        4. 请求中所携带的请求参数
```java
public ModeAndView doSome(HttpServletRequest req,HttpServletResponse res,HttpSession session){}
```

### 接收用户的参数
    1. 逐个接收 ：处理器方法的形参名和请求中参数名必须一致，同名的请求参数赋值给同名的形参
        注意：
            在 post 提交中文时，会出现中文乱码，这时候需要理由过滤器来解决乱码问题

**springmvc自带过滤器解决post 乱码**
```xml
<filter>
    <filter-name>filerName</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceRequestEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
    <init-param>
        <param-name>forceResponseEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
    <filter-mapping>
    <filter-name>filerName</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
**springmvc 实现过滤器源码，可以看出是通过forceRequestEncoding 和 forceResponseEncoding 来判断是否设置 encoding**
```java
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String encoding = this.getEncoding();
        if (encoding != null) {
        if (this.isForceRequestEncoding() || request.getCharacterEncoding() == null) {
        request.setCharacterEncoding(encoding);
        }

        if (this.isForceResponseEncoding()) {
        response.setCharacterEncoding(encoding);
        }
        }

        filterChain.doFilter(request, response);
        }
```
    
        @RequestParam ：解决请求中参数名形参名不一样的问题
            属性：value 请求中的参数名称
                 require 是一个boolean 默认是 true 表示请求中必须包含此参数
                 produces：使用这个属性定义新的 contentType
            位置：在处理器方法的形参定义的前面
```java
public ModeAndView receiveParam(@RequestParam("rname") String name
                                @RequestParam("rage") Integer age){}
```
    2. 对象接收 ：使用java对象来接受参数
        处理器方法形参是java 对象，这个对象的属性名和请求中的参数名是一样的
        框架或创建形参的java 对象，给属性赋值，请求参数是name 就会调用 setName

    3. 处理方法的返回值
        1. 返回 ModeAndView
            如果处理器方法处理完后，需要跳转到其他资源，而且又要在跳转的资源间传递数据，此时
            处理器方法返回 ModeAndView 比较好。当然，如果要返回 ModeAndView，则处理器方法
            中需要定义ModeAndView 对象
        2. 返回 String
            处理器方法返回的字符串可以指定逻辑视图名，通过视图解析器解析可以将其转换为物理视图地址
            框架执行一个转发操作
        3. 返回 void
            不能表示数据，也不能表示视图，在处理 ajax 时，就使用void 返回。通过HttpServletResponse 输出数据。
            响应ajax 请求。ajax 请求返回的就是数据，和视图无关
        4. 返回 Object
            处理方法也可以返回 Object 对象，这个Object 可以是Integer，String 自定义对象，Map，list
            等等，但返回的对象不是作为逻辑视图出现的，而是作为直接在页面显示的数据出现的。
            返回对象，需要使用 @ResponseBody 注解，将转换后的JSON 数据放入到响应体中
            
            实现步骤：
                1. 加入json 工具库的依赖，springmvc 默认使用jackson
                2. 在springmvc配置文件之间加入 <mvc:annotation-driven> 注解驱动
                3. 在处理器的上面加入 @ResponseBody 注解

            原理:
                springmvc 返回一个对象时，可以转为 json 输出到浏览器，响应 ajax 内部与阿尼
                1. <mvc:annotation-driven> 注解驱动
                    注解驱动实现的功能是，完成 java 对象到 json xml text 二进制等数据格式的转换
                    HttpMessageConveter 接口：消息转换器。
                    功能：定义了 java 转为 json、xml 等数据格式的方法。这个接口有很多实现类
                            这些实现类完成 java 对象多json、java 对象到xml ，java对象到二进制数据的转换

            @RequestBody 注解
                    框架处理流程：
                    1. 框架会返回该对象，调用框架中的ArrayList<HttpMessageConverter> 中每个类的canWrite()方法
                    检查那个HttpMessageConverter 接口的实现类能处理Student类型的数据
                    2. 框架调用实现类的write()，MappingJackson2HTTPMessageConvert的 writ() 方法，将返回的对象
                    转换为json 刁红jackson的objectMapper 实现转换为 json contenType：Application/json;chartset=utf-8
                    3. 框架会调用 @RespinseBody 把2 的结果输出到浏览器。

            当返回的值是 List 时 会转换为 JsonArray
            当有 @RequestBody 注解时，返回值是String 时就会自动转换为 json 格式

### 静态资源的处理方法
    当设置 url-pattren ——设置中央调度器的url-pattern 是 */* 时，会导致 静态资源访问失败
    第一种解决静态资源访问的方案：在springmvc 配置文件中 <mvc:default-servlet-handler> 
        原理：给程序内存中增加一个处理器对象，DefaultHttpRequestHandler，让这个对象处理静态资源
    第二种静态资源处理方法：<mvc:resource mapping="/static/**" location="/static/"/>
## 4. SpringMVC 核心技术
### 4.1 请求重定向和转发
        当处理器对请求处理完毕之后，向其它资源进行跳转时，有两种跳转方式：请求转发与重定向。
    而根据所要跳转的资源类型，又可以分为两类：跳转到页面与跳转到其他处理器。
        但是对于请求转发的页面，可以是WEB-INF下的页面，但是重定向不能是这个下的页面，因为用户是
    无法直接访问到这个路径下的资源的
    
    SpringMVC 将 servlet 中的请求转发和重定向进行了封装
    forward：表示转发
    redirect：表示重定向
---
    forward:
        从处理器方法返回 ModeAndView 实现转发 forward
        语法: setViewName("forward:视图文件完整路径")
        forward特点：不和视图解析器一同使用，就当项目中没有视图解析器。用于转发不在视图解析器的路径
    redirect:
        不和视图解析器一同使用，就当项目中没有视图解析器
        框架会吧Mode 中的简单数据转换为字符串，转换为string 使用，重定向的请求参数使用
    
### 4.2 异常处理
**SpringMVC 框架处理异常的常用方式，使用 @ExceptionHandler 注解处理异常**
#### 4.2.1 @ExceptionHandler 注解
        使用注解 @ExceptionHandler 可以将一个方法指定为异常处理方法。该注解只有一个可选属性 value，为一个Class<?> 数组
    用于指定该注解的方法所要处理的异常，即所要匹配的异常。
        而被注解的方法，起返回值可以是 ModeAndView String 或 void 方法名随意，方法的参数可以是Exception及其子类对象、
    HTTPServiceRequest、HTTPServiceResponse 等。系统会自动为这些方法参数赋值
        对于异常处理注解的用法，也可以直接将 异常处理方法注解与 Controller 之中
---
**统一全局异常处理**
> 把Controller中的所有异常处理都集中到一个地方，AOP 的思想，降低耦合
**异常处理步骤**
1. 创建 maven web 项目
2. 加入依赖
3. 创建一个自定义异常类， MyUserException，再定义它的子类NameException，AgeException
4. 在Controller 抛出这个异常
5. 创建一个普通类，作用全局异常处理类
   1. 在类的上面加入 ControllerAdvice
   2. 在类中定义方法，方法上面加入 @ExceptionHandler
6. 创建处理异常的视图界面
7. 创建 SpringMVC 配置文件
   1. 组件扫描器，扫描@Controller 注解
   2. 组件扫描器，扫描@ControllerAdvice所在的包名
   3. 声明注解驱动

**@ControllerAdvice ：控制器增强（给控制器增加功能---异常处理）**
    特点：必须让框架知道这个注解所在的包名，需要在springmvc 配置文件声明组件扫描器
    指定@ControllerAdvice 所在的方法

处理异常的方法和控制器方法的定义一样，可以有多个参数，可以有多个返回值ModeAndView、String、void 等

形参：Exception，表示Controller 中所抛出的异常对象
通过形参可以获取发生的异常信息

@ExceptionHandler(异常的class)：表示异常的类型，当发送此类型异常时，有当前方法处理

异常发生处理逻辑：
    1. 需要把异常记录下来，记录到数据库，日志文件
    记录时间，哪个方法发生的，异常错误内容
    2. 发送通知
    3. 给用户友好的提示

### 4.3 拦截器
    SpringMVC 中的Interceptor 拦截器是非常重要和相当有用的，它的主要作用是拦截指定的用户请求，并进行相应的预处理和后处理
    其拦截时间点在"处理器映射起根据用户提交的请求映射出了所要执行的处理器类而且也找到了要指定改处理器类的处理器适配器，
    在处理器适配器执行处理器之前"，当然，在处理器映射出所要执行的处理器类时，已经将拦截器与处理器和为了一个处理器执行链，
    并返回给中央调度器。

    1. 需要实现HandlerInterceptor 接口
    2. 拦截器和过滤器类似，但是过滤器主要用啦过滤请求参数，设置编码字符集等工作
    3. 拦截器是全局的，可以对多个Controller 做拦截
        一个项目中可以存在多个拦截器，一般使用在用户登录处理，权限检查，记录日志

#### 4.3.1 一个拦截器的执行

**拦截器使用步骤**
1. 定义类实现HandlerInterceptor 接口
2. 在SpringMVC 配置文件中声明拦截器。

**执行时间**
1. 在Controller类中的方法执行之前被拦截
2. 在控制器方法执行之后也会执行拦截器
3. 在请求处理完成后也会执行拦截器

```java
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    /**
     * preHandel 叫做预处理方法
     * 特点：
     *  1. 方法在控制器方法（MyController 的 doSome）之前先执行的
     *      用户的请求首先到达的方法
     *  2. 在这个方法中可以获取请求的信息，验证请求是否符合要求
     *      可以验证用户是否有权限连接某个地址
     *      如果验证失败，可以截断请求，请求不能被处理
     *      如果验证成功，可以放行请求，此时控制器方法才能执行。
     * @param handler 被拦截的控制器对象
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return true;  // 为 true 放行，为 false 不放行
    }


    /**
     * 后处理方法
     *
     * 特点：在 Controller 的doSome 方法后执行的，能够获取到处理器方法的返回值
     *      ModeAndView，可以修改ModeAndView中的数据和视图，可以影响到最后执行的结果
     *      主要是对结果做二次修正
     *
     * @param request
     * @param response
     * @param handler   被拦截器执行的的 Controller 对象
     * @param modelAndView  处理方法的返回值
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    /**
     * 最后执行的方法
     *
     * 特点：
     * 1、在请求处理完成后执行。框架中当视图处理完成后，对视图执行了forward 。就认为请求处理完成。
     * 2、一般做资源回收工作，把占用的内存回收
     * @param request
     * @param response
     * @param handler   被拦截的Controller 处理对象
     * @param ex        程序中发生的异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}

```
**拦截器：看作多个Controller 中公用的功能，就是 AOP 的思想**

**多个拦截器执行顺序**

在框架中保存的是一个 ArrayList 集合来存储，所以是按照配置的先后顺序
例如现在有两个拦截器，那么执行的顺序是
1-1 --> 2-1 --> 2-2 --> 1-2 --> 2-3 --> 1-3（第一个数字代表第几个拦截器，第二个数字代表对应的三个方法）
如果第一个为 true 第二个 为 false
1-1 --> 2-1 --> 1-3
第一个为false 时后面方法都不执行

**拦截器和过滤器的区别**
1. 过滤器是 servlet 中的对象，拦截器是框架中的对象。
2. 过滤器是实训 Filter接口对象，拦截器是实现 HandlerInterceptor
3. 过滤器是用啦设置 request，response的参数，属性，侧重对数据的过滤。拦截器是用来验证请求的
4. 过滤是在拦截器之前执行的
5. 过滤器是tomcat服务器创建的对象，拦截器是springMVC创建的对象
6. 过滤器是一个执行时间点，拦截器有三个执行时间点
7. 过滤器可以处理 jsp，js，html 等，拦截器是侧重 Controller 对象。如果请求不会被中央调度器接受，那就不会执行拦截内容
8. 拦截器拦截普通类方法执行，过滤器过滤Servlet请求响应

### 4.4 SpringMVC 处理流程
    1. 用户发起请求
    2. DispatcherServlet 接收请求，把对象交给处理器映射器
        处理器映射器：SpringMVC 中的一种对象，框架把事项了HandleMapping接口的类都叫做映射器
        处理器映射器的作用：根据请求，从SprintMVC容器对象中获取处理对象（ctx.getBean("beanName"));
        框架将找到的处理器对象放到一个叫做处理器执行链中（HandlerExecutionChain）的类保存
        HandlerExecutionChain 类中保存这：1. 处理器对象（MyController）2. 项目中所有拦截器 List<HandlerIntercept>
    3. DispatcherServlet 把 2 中的HandlerExecutionChain中的处理对象交给了处理器适配器对象（多个）
        处理器适配器：springMVC中的对象，需要实现HandlerAdapter 接口
        处理器适配器的作用：执行处理器方法（调用MyController.doSome()得到返回值ModeAndView）
    4. DispatcherServlet 把 3 中获取的ModeAndView 交给视图解析器对象
        视图解析器对象：springMVC中的对象，需要实现 ViewResoler接口（可以有多个）
        视图解析器的作用：组成视图完整路径，使用前缀，后缀并创建 view 对象
            view 是一个接口，表示视图的，在框架中 jsp、html 不是用 string 来表示的，而是使用view 和他的实现类表示视图
            InternalResourceView：视图类，表示jsp 文件，视图解析器会创建 InternalResourceView类对象
            这个对象的里面，有一个属性，是url 
    5. DispatcherServlet 把 4 步骤中的view 对象获取到，调用view 类中自己的方法让其对自己进行渲染，进行数据填充，形成响应对象
    6. 中央调度器响应浏览器
    
    核心方法 DispatcherServlet.doDispatch()