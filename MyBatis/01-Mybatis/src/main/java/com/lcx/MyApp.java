package com.lcx;

import java.io.IOException;

public class MyApp {
    public static void main(String[] args) throws IOException {
        /**
         * 主要类
         *      1. Resources:mybatis 中的一个累，负责读取主配置文件
         *          InputStream in = Resources.getResourcesAsStream("");
         *      2. SqlSessionFactoryBuilder : 创建 SqlSessionFactory 对象
         *          SqlSessionFactoryBuilder builder = new ....
         *          SqlSessionFactory factory = builder.build(in)
         *      3. SqlSessionFactory 重量级对象，程序常见一个对象耗时比较长，使用资源比较多
         *          在整个项目中，有一个就足够了
         *          SqlSessionFactory：接口 接口实现类：DefaultSqlSessionFactory
         *          SqlSessionFactory：作用 获取SqlSession对象。SqlSession sqlSession = factory。openSession()
         *
         *          openSession() 方法说明
         *              1. openSession() ： 无参数的，获取是飞自动提交事务的SqlSession 对象
         *              2. openSession(boolean)：传入参数是否自动提交事务
         *      4. SqlSession
         *          SqlSession接口：定义了操作数据库的方法
         *          SqlSession接口的实现累 DefaultSqlSession
         *
         *          使用要求：
         *              非线程安全的，使用前使用 openSession() 使用后执行 SqlSession.close()
         *       一个表对应一个接口，一个接口对应一个 mapper 文件
         *
         * 1. 动态代理:使用 SqlSession.getMapper(dao.class) 获取这个dao 接口的对象
         * 2. 传入参数：从java代码中吧数据传入到 mapper 文件的 sql 语句中
         *      1. 传入一个参数 parameterType：写在mapper 文件中的一个属性，表示dao接口方法的参数的数据类型
         *          用来表示 dao 接口方法参数的数据类型
         *              值是java参数的全限定名称，也能够简写
         *              不是强制的，mybatis 通过反射也能找到
         *              当传入值有一个参数时，#{任意字符}
         *              使用 #{} 之后，mybatis 执行 sql 是使用jdbc中的PreparedStatement对象
         *              由mybatis创建 connection ， PreparedStatement 对象
         *      2. 传入多个参数 使用 @param 命名参数
         *          在形参定义的前面 加入 @param 参数注释
         *
         *          用对象方式来传递：
         *              使用java 对象作为接口方法：#{属性名，javaType=类型名称，jdbcType=数据类型}
         *              javaType：指java中的数据类型
         *              jdbcType：指数据库中的类型
         *              例如：#{paramName，javaType=java.lang.String,jdbcType=VARCHAR}
         *              简写：#{paramName}
         *          按参位置
         *              3.3及其以前 #{0} #{1}
         *              3.4及其以后 #{arg0} #{arg1}
         *          Map 传值
         *              可读性差，不建议
         *          # 和 $ 的区别：
         *              # 占位符：
         *                  告诉 mybatis 用实际的参数值来代替，只用 prepareStatement 对象执行 sql 语句
         *                  #{...}代替sql 语句的 "？"。这种做更安全，更迅速，通常也是首选做法
         *              $ 占位符：
         *                  告诉 mybatis 使用 $ 包含的"字符串"替换所在位置。使用 statement 把 sql 语句和${}的
         *                  内容链接起来，主要用在替换表名，列名，不同列排序等
         * mybatis 的输出结果
         *      1. resultType 结果类型，指 sql 语句执行完毕以后，数据转为 java 对象
         *          处理方式：
         *              1. mybatis 执行 sql 语句，然后 mybatis 调用 无参构造方法，创建对象
         *              2. mybatis 把resultSet指定列值付给同名的属性
         *              3. 这个java 类型是任意的 不一定非要实体类
         *              4.一个简单类型：
         *                  将resultType 修改为 int 或者与返回值相同就行
         *      2. 定义自定义类型别名
         *          1. <typeAlias></typeAlias>
         *          map 同理
         *      3. 利用 resultMap 配置自定义列名和属性名的对应关系
         *          或者用 as 进行别名，将列属性值赋值给对象
         * 模糊查询
         *      1. 直接用占位符的方式
         *      2. 用拼接的方式
         *
         *
         *  动态标签
         *      <if><if/> <where><where/> <foreach><foreach/>
         *
         *      1. <if test="判断语句">
         *              部分 sql 语句
         *          <if/>
         *      2. <where> 用来包含多个<if>的， 当多个 if 有一个成立时，<where> 会自动添加一个
         *                  where 关键字，并且去掉if中多余的 and 和 or 等
         *      </where>
         *      3. <foreach><foreach/> 循环 java 中的数组，list 集合，主要用在 sql 的 in 语句中
         *          select * from user where id in {1,2,3}
         *
         *          <foreach collection="" item="" close="" separator=""></foreach>
         *
         *          collection 表示接口中的方法参数类似，如果是 数组为 array list 用list
         *          item：自定义的，表示数组和集合成员的变量
         *          close：循环结束时的字符
         *          separator：集合成员之间的分隔符
         *      4. sql 代码片段
         *          用来复用一下语法
         *          1. 先使用  <sql id="自定义名称唯一“> sql 语句 <sql/>
         *          2. 再使用  <include refid="id的值"/>
         * 属性配置文件
         *      1. 将数据库连接信息放到一个单独的文件
         *      2. 在mybatis的主配置文件中，使用 <property><property/> 指定配置文件的位置
         *          在需要使用的地方，使用 ${}
         *
         * 分页功能
         *
         */
    }

}
