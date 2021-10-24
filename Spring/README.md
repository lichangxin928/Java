# IOC 控制反转
    指将对象的创建，赋值，管理工作都交给代码之外的容器来实现，
    也就是说对象的创建是有其他外部资源完成
#### 控制
    创建对象，对象的属性赋值，对象之间的关系管理
#### 反转
    把原来的开发人员管理，创建对象的权限转移给代码之外的容器，
    油容器带他开发人员管理对象，创建对象，给对象赋值
#### 正转
    由开发人员在代码中，使用new 构造方法创建对象
    开发人员主动管理对象
````java
public static void main(String[]args){
    User user = new User(); // 在代码中，创建对象 -- 正转        
}

````
#### 容器: 是一个服务器软件，一个框架（Spring）

######目的：使用 ioc 是为了减少对代码的改动，也能实现不同的功能，实现解耦合

####java 中创建对象的方式：
    1. 构造方法
    2. 反射
    3. 序列化
    4. 克隆
    5. ioc：容器创建对象
    6. 动态代理（底层反射）

#### IOC 的体现
    servlet 1：在创建类继承 HttpServlet
            2. 在 web.xml 注册 servlet
            3. 没有创建 Servlet 对象
            4. Servlet 是 Tomcat 服务器创建的，Tomcat 也称为容器
                Tomcat 作为容器，里面存放的有servlet对象，Listener Filter 对象

####IOC 的技术实现（依赖注入 DI）
    DI 是 IOC的技术实现
    DI （Dependency Injection）：依赖注入，只需要才程序中提供要使用的对象名称就可以了，
                                至于对象如何在容器中创建赋值都是由容器内部来实现

Spring 是使用的DI实现了 IOC 功能，底层是利用了反射机制来实现的


# AOP 面向切面编程
#### 动态代理
    动态代理的实现方式有两种：使用JDK的 Proxy与通过 CGLIB生成代理，jdk的动态代理要求目标必须实现接口实现，这是 java 设计上的要求。
    从 jdk 1.3 以来，java 语言通过 java.lang.reflect 包提供三个类支持代理模式 Proxy Method InvocationHandler
#### 动态代理实现步骤
    1. 创建目标类
    2. 创建InvocationHandler接口的实现类，在这个类实现给目标方法增加功能
    3. 使用 jdk 中 类Proxy，创建代理对象。实现创建对象的能力

#### 动态代理实际实现

service 层接口：
```java
public interface SomeService {
    void doSome();
}
```

接口实现类：
```java
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println("do some");
    }
}
```

InvocationHandler 实现类
```java
public class MyInvocationHandler implements InvocationHandler {
    // 目标对象
    Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 通过代理对象执行这个方法时， 会调用执行这个invoke()
        Object res = null;
        if(method.getName() =="doSome"){
            ServiceTools.doLogs();
            // 执行目标类方法，通过method 来实现
            res = method.invoke(target, args);
            ServiceTools.end();
            // 返回
        }
        
        return res;
    }
}
```
测试main 方法：
```java
public class MyApp {
    public static void main(String[] args) {
        // 创建目标对象
        SomeService target = new SomeServiceImpl();

        // 创建 InvocationHandler对象
        InvocationHandler handler = new MyInvocationHandler(target);

        // 创建代理对象
        SomeService proxy = (SomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),handler);

        // 通过代码执行方法，会调用handler 中的 invoke()
        proxy.doSome();
    }
}
```

运行结果，达到通过动态代理对该方法进行加强：
```java
/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=53127:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/charsets.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/cldrdata.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/dnsns.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/jaccess.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/jfxrt.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/localedata.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/nashorn.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/sunec.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/ext/zipfs.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/jce.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/jfr.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/jfxswt.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/jsse.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/management-agent.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/resources.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/jre/lib/rt.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/lib/ant-javafx.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/lib/dt.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/lib/javafx-mx.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/lib/jconsole.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/lib/packager.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/lib/sa-jdi.jar:/Users/chiaochunhuang/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home/lib/tools.jar:/Users/chiaochunhuang/Desktop/code/Java/Spring/out/production/04-Spring-aop com.lcx.MyApp
当前时间为Sat Oct 23 10:40:54 CST 2021
do some
程序执行结束

进程已结束，退出代码为 0
```

##### 动态代理的作用
    1. 在原代码不变的情况下，增加功能
    2. 减少代码的重复
    3. 专注业务逻辑代码
    4. 解耦合，书屋和非业务功能分离

#### AOP 简介
    AOP(Aspect Orient Programming),面向切面编程。面向切面编程是从动态角度考虑程序运行过程
    AOP底层，就是采用动态代理模式实现的。采用了两种代理：JDK 动态代理和CGLB 的动态代理
    AOP 就是动态代理的规范化，把动态代理的实现步骤，方式都定义好了，开发人员用一种统一的方式，使用动态代理
---
    AOP 为 Aspect Oriented Programming 的缩写，意为:面向切面编程，可通过运行期动态，
    代理实现程序功能的统一维护的一种技术。AOP 是 Spring 框架中的一个重要内容。利用 AOP 
    可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程 序的可重用性，
    同时提高了开发的效率。
---
    面向切面编程，就是将交叉业务逻辑封装成切面，利用 AOP 容器的功能将切面织入到 主业务逻辑中。
    所谓交叉业务逻辑是指，通用的、与主业务逻辑无关的代码，如安全检查、 事务、日志、缓存等。
    若不使用 AOP，则会出现代码纠缠，即交叉业务逻辑与主业务逻辑混合在一起。这样， 会使主业务逻辑变的混杂不清。
    例如，转账，在真正转账业务逻辑前后，需要权限控制、日志记录、加载事务、结束事 务等交叉业务逻辑，
    而这些业务逻辑与主业务逻辑间并无直接关系。但，它们的代码量所占 比重能达到总代码量的一半甚至还多。
    它们的存在，不仅产生了大量的“冗余”代码，还大 大干扰了主业务逻辑---转账。

#### 怎么理解面向切面编程？
    Aspect：给目标类额外增加的功能，就是切面。
            切面的特点：一般都是非业务方法，能够独立使用的
    Orient：面向，对着
    Programming：编程

    OOP：Object Orient Programming 面向对象编程
---
    1. 在分析项目功能时，找出切面
    2. 合理的安排切面的执行时间
    3. 合理的安排切面执行的位置
---
#### 术语：
    1. Aspect：切面，边上增强的功能，就是一堆代码，完成某一个功能。非业务功能
               常见的切面功能有日志，事物，统计信息，参数检测，权限验证
    2. JoinPoint：连接点，连接业务方法和切面的位置。就某类中的业务方法
    3. Pointcut：切入点，指多个连接点方法的集合，多个方法
    4. 目标对象：给哪个类增加功能， 这个类就是目标对象
    5. Advice：通知，通知表示切面功能执行时间

    切面三个关键要素
        1. 切面的功能代码，切面要干什么
        2. 切面的执行位置，使用 Pointcut表示切面的位置
        3. 切面执行时间，用Advice 表示切面执行的时间

#### AOP 的实现
    aop 是一个规范，是动态代理的一个规范化，一个标准
    aop 的技术实现框架
    1.spring：spring 内部实现了aop规范，能做aop 的工作
              spring主要在事务处理时使用 aop
              项目中很少使用 spring aop 实现操作，因为spring 的 aop比较笨重
    2. aspectJ：一个开源的专门叫做 aop 的框架，spring 框架中集成了 aspectJ 框架
                通过 spring 就能使用aspectJ 功能
                aop 的框架实现由两种方式
                    1. 使用 xml 的配置文件：配置全局事务
                    2. 使用注解，我们在项目中要做 aop 功能，一般都是使用注解，aspectJ 有五个注解

#### aspectJ 框架的使用
    1. 切面的执行时间，这个执行时间在规范中叫做 Advice（通知，增强）
        1. @Before
        2. @AfterReturning
        3. @Around
        4. @AfterThrowing  
        5. @After
    2. AspectJ的切入表达式
```java
execution(modifiers-pattern? ret-type-pattern
declaring-type-pattern?name-pattern(param-pattern)
throws-pattern?)

/**
 * 解释:
 *  modifiers-pattern] 访问权限类型
 *  ret-type-pattern 返回值类型
 *  declaring-type-pattern 包名类名 name-pattern(param-pattern) 方法名(参数类型和参数个数) throws-pattern 抛出异常类型
 *  ?表示可选的部分
 *  
 *  
 *  以上表达式共 4 个部分。
 *  execution(访问权限 方法返回值 方法声明(参数) 异常类型)
 *
 *  符号        意义
 *   *          0到多个字符
 *   ..         用在方法参数中，表示任意多个参数，用在包名后，表示当前包及其子包
 *   +          用在类名后面，表示当前类及其子类，用在接口后面，表示当前接口及其实现类
 * 
 * 举例
 * execution(public * *(..))
 * 指定切入点为:任意公共方法。
 * execution(* set*(..))
 * 指定切入点为:任何一个以“set”开始的方法。 
 * execution(* com.xyz.service.*.*(..))
 * 指定切入点为:定义在 service 包里的任意类的任意方法。 
 * execution(* com.xyz.service..*.*(..))
 * 指定切入点为:定义在 service 包或者子包里的任意类的任意方法。“..”出现在类名中时，后 面必须跟“*”，表示包、子包下的所有类。
 * execution(* *..service.*.*(..))
 * 指定所有包下的 service 子包下所有类(接口)中所有方法为切入点
 * execution(* *.service.*.*(..))
 * 指定只有一级包下的 service 子包下所有类(接口)中所有方法为切入点
 * execution(* *.ISomeService.*(..))
 * 指定只有一级包下的 ISomeService 接口中所有方法为切入点
 * execution(* *..ISomeService.*(..))
 * 指定所有包下的 ISomeService 接口中所有方法为切入点
 * execution(* com.xyz.service.IAccountService.*(..))
 * 指定切入点为:IAccountService 接口中的任意方法。
 */
```
#### AOP 例子
> 目的是使用 AspectJ 所封装好的动态代理，使用注解来对业务层的方法进行加强
> 此例子是在方法执行前输出当前时间

首先是Service层的一个接口
```java

public interface SomeService {
    void doSome();
}

```

实现这个接口
```java
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        // 给 doSome 增加一个方法
        System.out.println("do some....");
    }
}

```

切面类
```java
import java.util.Date;

/**
 * @Aspect 表示当前类是切面类，这个类中有切面的功能代码
 */
@Aspect
public class MyAspect {
    /**
     * 定义方法，实现切面功能
     *  1. 功能方法，public
     *  2. 方法没有返回值
     *  3. 方法可以有参数，也可以没有参数
     *  4. 方法名称自定义
     */

    /**
     *
     * @Before 前置通知注释
     *  属性 value 是切入点表达式，表示执行的位置
     *  位置 在方法的上面
     */

    @Before(value = "execution(public void com.lcx.bao1.SomeServiceImpl.doSome())")
    public void myBefore(){
        System.out.println("当前时间为：" + new Date());
    }
}

```
spring 配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">
<!--    对象交给 spring 容器管理-->
<!--    声明目标对象-->
    <bean id="someService" class="com.lcx.bao1.SomeServiceImpl"/>
<!--    声明切面类对象-->
    <bean id="myAspect" class="com.lcx.bao1.MyAspect"/>
<!--
    声明自动代理生成器：使用aspect 框架内部的功能，创建目标对象的代理对象
    创建代理对象是在内存中实现的，修改目标对象的内存中的结构，创建为代理对象
    所以目标对象就是被修改后的代理对象

    aspectj-autoproxy 会自动将spring中的目标对象生成代理对象
-->
    <aop:aspectj-autoproxy/>
</beans>
```

测试类
```java
public class AppTest 
{

    @Test
    public void shouldAnswerWithTrue() {
        String config = "applicationContext.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        SomeService someService = (SomeService) context.getBean("someService");
        someService.doSome();
    }
}
```

运行结果
```
当前时间为：Sat Oct 23 14:03:11 CST 2021
do some....

进程已结束，退出代码为 0
```

**可以看出，通过注解的方式，利用 AspectJ 实现了不更改原方法的情况下**
**使该方法有了更多的功能**