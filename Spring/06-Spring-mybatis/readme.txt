spring 和 mybatis 的集成

步骤：
    1. 新建项目
    2. 加入maven 依赖
        1. 加入 spring 依赖
        2. mybatis 依赖
        3. mysql 依赖
        4. spring 的事务依赖
        5. mybatis 和 spring 集成的依赖
    3. 创建实体类
    4. 创建 dao 接口和mapper 文件
    5. 创建 mybatis 主配置文件
    6. 创建 Service 接口和实体类
    7. 创建 spring 的配置文件，声明mybatis 的对象交给 spring 创建
        1. 数据源
        2. SqlSessionFactory
        3. Dao对象
        4. 声明自定义的Service
    8. 创建测试类，获取 Service 对象，通过 Service 调用 dao 完成数据库的访问