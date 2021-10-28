# SSM 整合开发 （SpringMVC + Spring + Mybatis）

对于刚刚学习完 SSM 框架的 Java 开发者来说，将三个框架整合在一起并非容易的事情，特别是 ssm 中各种各样的配置文件
如果一步小心哪里写错了，就会导致服务器启动不起来，然后花费大量的时间去寻找错误，到最后甚至删除项目重新创建，于是我将
整合 SSM 的步骤分享出来，从 数据库 再到配置文件再到业务代码，最终实现对数据库表中的数据完成插入操作以及 ajax 返回
查询数据。


## 一、概述：
> SpringMVC:视图层，界面层，负责接收请求，显示处理结果
> Spring:业务层，管理 Service ，dao，工具类对象
> Mybatis:持久层，访问数据库
>
> 用户发起请求 --- SpringMVC 接收 --- Spring中的Service对象 --- Mybatis 处理数据

**ssm 整合也叫做ssi，整合中有容器
1. 第一个容器SpringMVC，管理Controller 控制器对象
2. 第二个容器Spring容器，管理Service，Dao，工具类对象等
我们要做的就是吧使用的对象交给合适的容器创建，管理。把Controller还有web开发的相关对象
交给SpringMVC 容器，这些 web 用的对象在SpringMVC 配置中

Service，dao 对象定义在 spring 的配置文件中，让spring 管理这些对象。

SpringMVC 容器和 Spring 容器是有关系的，关系以及确定好了
SpringMVC 容器是 Spring 的子容器，类似java 中的继承。子可以访问父的容器
在子容器中的Controller 可以访问父容器中的 Service 对象，就可以实现Controller 使用Service对象**

## 二、实现步骤：
    0. 使用 SpringDB 的 mysql
    1. 新建maven web 项目
    2. 加入依赖
        ssm 三个框架的依赖，jackson依赖，mysql，druid 连接池
        jsp，servlet
    3. 写web.xml 
        1. 注册 DispatcherServlet，目的：1.创建springmvc 容器对象，才能创建Controller 对象，创建的是Servlet才能接受用户请求
        2. 注册spring 监听器：ContextLoaderListener，目的：创建 spring 的容器对象，才能创建 Service dao等对象
        3. 字符集过滤器，解决post 请求乱码问题
    4. 创建包，Controller包，Service 包， Dao 包，实体类包
    5. 写springmvc，spring，Mybatis 的配置文件

## 三、配置文件及其代码

### 1. 首先创建 mysql 表

```mysql
create table student(
    id int primary key auto_increment,
    name varchar(30) not null ,
    age int not null 
) 
```
---
### 2. 创建好数据库表之后，就在idea 里面创建一个 JavaWeb 项目（既然大家都学到这里了，创建项目大家应该都会了，就不做演示了）
将MVC三层构建所对应的包创建好：Controller，dao，service，domain，utils
### 3. maven 依赖：pom.xml 文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.lcx</groupId>
  <artifactId>SSM</artifactId>
  <version>1.0-SNAPSHOT</version>
  <name>SSM</name>
  <packaging>war</packaging>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
      <junit.version>5.7.1</junit.version>
  </properties>

  <dependencies>
    <dependency>
      <!--      servlet-->
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>                                                                        
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>
<!--      jsp 依赖-->
      <dependency>
          <groupId>javax.servlet.jsp</groupId>
          <artifactId>jsp-api</artifactId>
          <version>2.2.1-b03</version>
          <scope>provided</scope>
      </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.9.0</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.9.0</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.9.0</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.1</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.1</version>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.9</version>
    </dependency>
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.12</version>
    </dependency>
  </dependencies>
  <build>
    <resources>
      <resource>
        <directory>src/main/java</directory>
        <includes>
          <include>**/*.properties</include>
          <include>**/*.xml</include>
        </includes>
        <filtering>false</filtering>
      </resource>
    </resources>
  </build>

</project>
```
---
### 4. 在 web.xml 文件中注册中央处理器，spring 监听器，全局过滤器（设置post请求 字符编码为 utf-8）
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--    web.xml 中注册中央调度器-->
    <servlet>
        <servlet-name>MyWeb</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--        初始化参数-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:config/dispatcherServlet.xml</param-value>
        </init-param>
        <!--        设置优先级-->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyWeb</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>

    <!--spring的上下文-->


    <!--    注册 Spring 的监听器 服务器启动就创建 spring 容器-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:config/applicationContext.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <!--    注册监听器-->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
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
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```
---
### 5. SpringMVC 配置文件，声明组件扫描器，注解开发支持，以及WEB-INF路径 dispatcherServlet.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
<!--    SpringMVC 配置文件 -->

<!--    组件扫描器-->
    <context:component-scan base-package="ssm.lcx.controller"/>

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    <mvc:annotation-driven/>
<!--
    1. 响应 ajax 请求，返回 json
    2. 解决静态访问资源
-->

</beans>
```
---
### 6. 创建 Spring 配置文件：applicationContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
<!--    spring 配置文件 声明 Service dao util-->

<!--    声明数据源，连接数据库-->
    <context:property-placeholder location="classpath:config/jdbc.properties"/>

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.psw}"/>
    </bean>

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:config/mybatis.xml"/>

    </bean>

<!--    声明 mybatis 扫描器-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <property name="basePackage" value="ssm.lcx.dao"/>
    </bean>
<!--    Service 所在位置-->
    <context:component-scan base-package="ssm.lcx.service"/>

<!--    事务配置：注解的配置，aspect 的配置-->

</beans>
```
---
### 7. 含有数据库连接信息的 jdbc.properties 文件

```properties
# 加上 characterEncoding=utf-8 可以防止乱码错误
jdbc.url=jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC
jdbc.username=root
jdbc.psw=123
```
### 8. mybatis配置文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
<!--    实体类的包名-->
    <typeAliases>
        <package name="ssm.lcx.domain"/>
    </typeAliases>

    <mappers>
<!--        使用package 的要求
            1. 文件名和dao 接口必须完全一致
            2. 文件名和接口名称比较一样
-->
        <package name="ssm.lcx.dao"/>
    </mappers>
</configuration>

```
---

### 9. 编写domain（实体类）代码，dao 层代码，service 层代码，Controller 层代码
1. domain

```java
package ssm.lcx.domain;

import java.util.Objects;

public class Student {
    private Integer id;
    private String name;
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) && Objects.equals(getName(), student.getName()) && Objects.equals(getAge(), student.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge());
    }

    public Student() {
    }

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

```

2. dao 接口以及对应的 mybatis—mapper 文件
```java
package ssm.lcx.dao;

import ssm.lcx.domain.Student;

import java.util.List;

public interface StudentDao {
    int insertStudent(Student student);
    List<Student> selectStudent();


}
```
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="ssm.lcx.dao.StudentDao">
    <select id="selectStudent" resultType="ssm.lcx.domain.Student">
        select id,name,age from student order by id
    </select>
    <insert id="insertStudent" parameterType="ssm.lcx.domain.Student">
        insert into student(name,age) value (#{name},#{age})
    </insert>

</mapper>

```
```
3. service 接口和实现类
```java
package ssm.lcx.service;

import ssm.lcx.domain.Student;

import java.util.List;

public interface StudentService {
    int addStudent(Student student);
    List<Student> findStudent();
}

```
```java
package ssm.lcx.service.impl;

import org.springframework.stereotype.Service;
import ssm.lcx.dao.StudentDao;
import ssm.lcx.domain.Student;
import ssm.lcx.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    // 引用类型的自动注入
    @Resource
    private StudentDao studentDao;
    @Override
    public int addStudent(Student student) {
        int num = studentDao.insertStudent(student);
        return num;
    }

    @Override
    public List<Student> findStudent() {
        List<Student> list = studentDao.selectStudent();
        return list;
    }
}

```

4. controller 代码实现
```java
package ssm.lcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.lcx.domain.Student;
import ssm.lcx.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService service;
    // 注册学生
    @RequestMapping("/addStudent.do")
    public ModelAndView addStudent(Student student){
        String tips = "注册失败";
        ModelAndView mv = new ModelAndView();
        int nums = service.addStudent(student);
        if (nums > 0) {
            // 注册成功
            tips = "学生 " +student.getName()+ " 注册成功";
        }
        mv.addObject("tips",tips);
        mv.setViewName("result");
        return mv;
    }
    // 处理查询，响应 ajax
    @RequestMapping("/queryStudent.do")
    @ResponseBody
    public List<Student> queryStudent(){
        List<Student> students = service.findStudent();
        return students;
    }
}

```


**最终完成整个 SSM 整合**