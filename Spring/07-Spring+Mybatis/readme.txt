事务：
    1. 什么是事务
        一串的 sql 语句，要么都成功，要么都失败，表示事务
    2. 在什么时候使用事务
        当我的操作涉及到 多个表，或者是多个表的 insert update delete，就需要保证
        这些语句都是成功才能完成功能，或者都失败，保证操作是符合要求的

        通常在Service类的业务方法上，执行多个 sql 语句
    3. 通常使用jdbc 访问数据库，还是mybatis访问数据库怎么处理
        jdbc 访问数据库，处理事务 Connection conn conn.commit() conn.rollback()
        mybatis sqlSession.commit() sqlSession.rollback()
    4. 3 问题中事务的处理方式的不足
        1. 不同的数据库，处理事务的方法不同
        2. 掌握多种数控中事务的处理逻辑，声明时候提交事务，声明时候回顾事务
        3. 处理事务的多种方法

        总的来说就是多种数据库访问技术，有不同的事务处理的机制，对象，方法
    5. 怎么解决不足
        spring 提供了一种处理事务的统一模型，能使用统一步骤，方式完成多种不同数据库访问技术的事务处理

        使用 spring 的事务处理机制，可以完成mybatis 访问数据库的事务处理

    6. 处理事务，需要怎么 做，做什么
        spring处理事务的模型，使用的步骤都是固定的。把事务使用的信息提供给spring就可以了

            1. 事务内部提交，回滚事务，使用的事务管理器对象，代替完成 commit rollback
            接口：PlatformTransaction，定义了事务重要方法，commit， rollback
            实现类：spring 把每种数据库访问技术对应的事务处理类都创建好了
                mybatis访问数据库 ------- DataSourceTransactionManager
                hibernate数据库  -------HibernateTransactionManager

            如何使用：
                需要告诉 spring 你用哪种数据库的访问技术，声明数据库范围技术对于事务的实现类
                ，在spring 的配置文件中使用 <bean> 声明就可以了

                例如：使用mybatis 访问数据库，

            说明需要使用的事务：
                事务的隔离级别
                任务的超时时间
                事务的传播行为
                    REQUIRED：添加事务
                    SUPPORTS：有和没有都可以
                    REQUIRE_NEW：一定是新建的事务

            提交事务和回滚事务
                1. 如果没有抛出异常就提交
                2. 当抛出运行时异常，就执行回滚
                    运行时异常：RuntimeException 与其子类
                3. 如果不是运行时异常会提交事务 主要是受查异常与ERROR


Spring 事务：
    1. 管理事务的是，事务管理器和他的实现类
    2. spring 的事务是一个统一模型
        1. 指定要使用的事务管理器来实现
        2. 指定哪些类，哪些方法需要加入事务的功能
        3. 指定方法需要的隔离级别，传播行为，超时

    需要告诉spring ，你的项目中类信息，方法名称，方法的事务传播行为




