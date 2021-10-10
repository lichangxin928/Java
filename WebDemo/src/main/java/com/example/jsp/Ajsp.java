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
     *
     */
}
