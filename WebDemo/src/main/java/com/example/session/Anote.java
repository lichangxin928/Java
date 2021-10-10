package com.example.session;

public class Anote {
    /**
     * 1. 概念：服务器端会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器端的对象中。HttpSession
     * 2. 快速入门
     *      1. 获取 Session对象
     *          request.getSession()
     *      2. 使用 HttpSession对象
     *          Object getAttribute(String name)
     *          void setAttribute(String name,Object value)
     *          void removeAttribute(String name)
     * 3. 原理：
     *      Session的实现是依赖于 cookie 的，创建一个 session 会携带 session的id加在cookie上
     * 4. 细节
     *      当客户端关闭后，服务器不关闭，两次获取 session
     *          不是同一个，如果需要相同，可以创建 cookie 设置JSESSIONID，设置最大存活时间
     *      客户端关闭，服务器不关闭，两次获取的 session
     *          不是同一个，但是要确保数据不丢失
     *              session 的钝化
     *                  在服务器关闭前将session对象系列化到硬盘上
     *              session 的活化
     *                  在服务器启动后，将session文件转化为内存中的session对象即可
     *      session 的失效时间
     *          1. 服务器关闭
     *          2. session 调用 invalidate()
     *          3. 30分钟默认失效
     * 5. session 的特点
     *      1. session 是用于一次会话的多次请求的数据，在服务器
     *      2. session 可以存储任意类型，任意大小
     *
     *      session 与 cookie 的区别
     *          1. session 在服务器，cookie 在客户端
     *          2. session 没有数据大小限制，cookie 有
     *          3. session 比较安全，cookie 相对不安全
     *
     *
     *
     *
     *
     */
}
