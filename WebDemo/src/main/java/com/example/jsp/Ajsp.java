package com.example.jsp;

public class Ajsp {
    /**
     * 1. 概念
     *      java server page 写在服务端的页面
     *      用于简化书写
     * 2. 原理
     *      jsp 本质上是一个 servlet
     * 3. jsp 脚本
     *      jsp 定义java代码的方式
     *          1. <% %> :定义的代码在 service 中，service 中能干嘛这里面就能干嘛
     *          2. <%! %> :定义的代码在，jsp 转换后的类中
     *          3. <%= %> :定义的代码会输出到页面上，并且在 service 中
     * 4. jsp 内置对象
     *      在 jsp 页面中不需要获取和创建，可以直接使用的对象
     *      jsp 一共9个内置对象。
     *      request、
     *      response、
     *      out: 字符输出流对象，可以将数据输出到页面上。 和 response.getWriter() 类似
     *          response.getWriter().write() 总是先于 out 输出
     *          因为在 tomcat 服务器真正给客户端做出反应是，会先找 response 缓存区数据，再找out 缓存区数据
     *      pageContext
     *      session
     *      application
     *      page
     *      config
     *      exception
     * 5. jsp 指令
     *      作用：用于配置 jsp 页面，导入资源文件
     *      格式
     *          <%@ 指令名称 属性名1=属性值1 属性名2=属性名2 %>
     *      分类：
     *          1. page :配送 jsp 标签
     *              contentType:
     *                  设置响应体的 mime 类型
     *                  设置当前 jsp 的编码
     *              import :导入包
     *              errorPage：当前页面发生异常后，会跳到error 页面
     *              isErrorPage：标识是否 error 页面
     *          2. include ：页面包含是。导入页面的资源文件
     *              导入能够复用的页面
     *          3. taglib ：导入资源
     * 6. 注释
     *      1. <!---->
     *      2. <%-- --%>
     * 7. 内置对象详解
     *      变量名                     真实值                     作用
     *    pageContext               PageContext             当前页面共享数据，获取其他八个内置对象
     *    request                   HTTPServletRequest      一次请求访问的多次资源
     *    session                   HttpSession             一次会话的多个请求
     *    application               ServletContext          所有用户间共享数据
     *    response                  HttpServletResponse     响应对象
     *    page                      Object                  当前对象 this
     *    out                       JspWriter               输出数据到页面
     *    config                    ServletConfig           Servlet 配置对象
     *    exception                 Throwable               异常对象
     *
     * 8. MVC 开发模式
     *      1. jsp 演变历史
     *          1. 早期只有 Servlet，只能使用response输出标签数据，非常麻烦
     *          2. jsp 出现，简化了 Servlet 开发
     *          3. MVC 模式出现
     *      2. MVC 开发模式
     *          1. M：model
     *              完成具体业务操作，如封装数据库，封装对象
     *          2. V：View
     *              展示数据
     *          3. C：Controller
     *              获取用户输入
     *              调用模型
     *              将数据交给视图进行展示
     *          优点：
     *              耦合低，利于分工
     *              重用性高
     *          缺点：
     *              项目架构变得复杂
     * 9. EL 表达式
     *      1. Expression Language 表达式语言
     *      2. 作用：替换和简化jsp 页面中的java代码编写
     *      3. 语法 ${表达式}
     *      4. jsp 默认支持 el表达式，可以在 page 配置中取消
     *      5. 使用
     *          运算：+-* /(div) %(mod)
     *              比较
     *              逻辑运算符：$$(and) ||(or) !(not)
     *              空运算符：empty
     *           获取值：
     *              1. 只能从域对象中获得指定值
     *                  域名称
     *                      1. pageScope  -- > pageContext
     *                      2. requestScope --> request
     *                      3. sessionScope --> session
     *                      4. applicationScope --> application(ServletContext)
     *                  在 request 域中存储了 name = 张三
     *                  获取：$(requestScope.name)
     *
     *              2. ${name} 表示从最小的域名开始查找，直到找到为止
     *              3. 获取值是对象
     *                  1. 获取对象 ${域名称.键名.属性名}底层调用的 getName() 方法
     *                  2. List 集合： ${域名称.键名.索引}
     *                  3. Map 集合：${域名.键名.key}
     *
     *      6. 隐式对象
     *          el表达式中由11个隐式对象
     *          pageContext
     *              获取其他jsp8个内置对象
     * 10.JSTL 标签
     *      1. 概念 JavaServer Pages Tag Library
     *          是由 Apache 组织提供的开源的免费的 jsp 标签
     *      2. 作用：
     *          用于简化和替换 jsp 页面上的java 代码
     *      3. 使用步骤：
     *          1. 导入 jstl 相关 jar包
     *          2. 引入标签库：<%@ taglib %>
     *          3. 使用标签
     *      4. 常见的JSTL 标签
     *          1. if ：相当于 java 的if
     *              属性：test ：判断的条件
     *          2. choose ：switch
     *              属性：
     *          3. foreach ： for
     *              begin:开始值
     *              end：结束值
     *              var：临时变量
     *              step：步长
     *              varStatus：循环对象
     *                  index：容器中元素的索引
     *                  count：循环次数，从1开始
     *
     *              items：容器对象
     *              var：容器元素的临时变量（迭代的变量）
     *              varStatus：循环状态对象
     * 11. 三层架构
     *      1. 界面层（表示层）：用户看的层面。用户可以通过界面上的组件和服务器进行交互
     *      2. 业务逻辑层：处理业务逻辑的。
     *      3. 数据访问层：操作数据存储文件
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
