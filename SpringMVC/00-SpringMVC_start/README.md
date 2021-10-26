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
## 3. springmvc 执行过程源码分析：
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
        