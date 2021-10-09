package com.example.reponse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Http 协议：
 *      1. 请求消息：客户端发送给服务器的数据
 *          数据格式：
 *              1. 请求行
 *              2. 请求头
 *              3. 请求空行
 *              4. 请求体
 *      2. 响应格式：服务器端发送给客户端的数据
 *          数据格式：
 *              1. 响应行
 *                  1. 组成：协议/版本 响应状态码 状态码描述
 *                  2. 响应状态码：服务器告诉客户浏览器本次请求和响应的一个状态
 *                      1. 状态码都是三位数字
 *                      2. 1xx: 服务器就收客户端消息，但没有接受完成，等待一段时间后，发送1xx多状态码
 *                         2xx: 成功
 *                         3xx: 重定向。代表：302 访问缓存：304
 *                         4xx: 客户端错误 404 请求路径没有资源 405 请求方式没有对应方法
 *                         5xx: 服务端错误 500 服务器内部出现异常
 *              2. 响应头
 *                  1. 格式: 头名称:值
 *                  2. 常见响应头:
 *                      1. Content-Type:服务器告诉客户端本次响应数据格式以及编码方式
 *                      2. Content-disposition:服务器告诉客户端以什么方式打开响应体数据
 *                          值：
 *                              in-line：默认值，在当前网页打开
 *                              attachment：filename=xxx 以附件形式打开响应体。文件下载
 *              3. 响应空行
 *              4. 响应体: 响应数据
 *
 * Response 对象
 *      功能：设置响应消息
 *          1. 设置响应行
 *              1. 格式： http/1.1 200 ok
 *              2. 设置状态码：setStatus(int sc)
 *          2. 设置响应头
 *              setHeader(String name,String value)
 *          3. 设置响应体
 *              使用步骤：
 *                  1. 获取输出流
 *                      字符输出流 PrintWriter getWriter()
 *                      字节输出流 ServletOutputStream getOutputStream()
 *                  2. 使用输出流，将数据输出到客户端浏览器
 *
 * 案例：
 *      1. 重定向
 *          重定向：资源跳转的方式
 *          代码实现：
 *              1. 设置状态码
 *              response.setStatus(302);
 *              2. 设置响应头
 *              response.setHeader("location",url);
 *          简单的实现方法：
 *          response.sendRedirect(url);
 *
 *          重定向特点：
 *              1. 地址栏发生变化
 *              2. 能够访问其他站点
 *              3. 重定向是两次访问,不能使用request对象来共享数据
 *          转发的特点：
 *              1. 地址栏不发生变化
 *              2. 不能访问其他站点
 *              3. 转发是一次访问，可以使用request对象来共享数据
 */
public class Response extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
