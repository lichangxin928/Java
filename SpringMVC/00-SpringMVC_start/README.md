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