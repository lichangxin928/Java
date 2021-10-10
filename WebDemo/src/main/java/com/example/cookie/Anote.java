package com.example.cookie;

public class Anote {
    /**
     * 1. 会话技术
     *      1. cookie
     *      2. session
     * 2. jsp
     *
     *
     * 会话技术：
     *      1. 会话：一次会话中包含多次请求
     *          一次会话：浏览器第一次给服务器发送请求，会话建立，直到有一方断开为止
     *      2. 功能：在一次会话的范围内的多次请求，共享数据
     *      3. 方式：
     *          1. 客户端会话:cookie
     *          2. 服务器端会话技术：session
     *
     * cookie：
     *      1. 概念：客户端会话技术，将数据保存到客户端
     *      2. 使用步骤
     *          1. 创建 cookie 对象
     *              new Cookie(String name,String value)
     *          2. 发送 cookie 对象
     *              response.addCookie(Cookie cookie)
     *          3. 获取 cookie ，拿到数据
     *              Cookie[] request.getCookie
     *
     *      3. 实现原理
     *          基于响应头 set-cookie 和请求头 cookie 实现
     *      4. cookie 的细节
     *          1. 一次发送多个cookie
     *              创建多个cookie对象，调用多次 response.addCookie 即可
     *          2. 保存多长时间
     *              1. 默认情况下，浏览器关闭 cookie数据被销毁
     *              2. 持久化存储
     *                  setMaxAge(int seconds)
     *                      1. 正数：将cookie数据写到硬盘文件中
     *                      2. 负数：默认情况，表示浏览器关闭数据被销毁
     *                      3. 零：删除数据
     *          3. 存储中文
     *              在tomcat8 之前不能直接存储中文数据，8之后能够直接存储中文数据
     *                  需要将中文数据转码
     *          4. 共享问题
     *              在一个服务器中部署了多个web项目，默认不共享 cookie
     *
     *              setPath(String path) :设置 cookie 的取值范围，默认情况下是设置当前的虚拟目录
     *              如果要共享，可以将path 设置为 /
     *
     *              不同的服务器间的 cookie 也能够共享
     *                  setDomain(String path) :如果设置一级域名相同，那么多个服务器之间的cookie可以共享
     *
     *           5. cookie 的特点
     *              1. 存储在客户端
     *              2. 浏览器对单个cookie的大小有限制，对同一个域名下总cookie大小有限制
     *
     *              作用：
     *                  1. cookie 一般用于存储少量不太敏感的数据
     */
}
