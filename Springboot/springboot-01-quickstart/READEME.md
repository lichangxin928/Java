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




