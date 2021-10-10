package com.example.ServletContect;

public class Note {
    /**
     * ServletContext 对象
     *      1. 概念：代表整个web应用，可以和程序的容器（服务器）来通信
     *      2. 获取：
     *          1.通过 request 对象获取
     *              request.getServletContext();
     *          2.通过 httpServlet 来获取
     *      3. 功能
     *          1. 获取MIME类型
     *              MIME类型：在互联网通信过程中定义的一种文件数据类型
     *                  格式：大类型/小类型  text/html   image/jpg
     *              获取：String getMimeType(String file)
     *          2. 域对象：共享数据
     *              1. setAttribute(String name,Object value)
     *              2. getAttribute(String name)
     *              3. removeAttribute(String name)
     *          3. 获取文件的真实(服务器)路径
     *              1. 方法:String getRealPath(String  path)
     *
     *  案例：
     *      文件下载案例
     *          1. 页面显示超链接
     *          2. 点击超链接后弹出下载提升框
     *          3. 完成图片文件下载
     *      分析：
     *          1. 超链接指向的资源如果能够被浏览器解析，则在浏览器中展示，如果不能解析，则弹出下载提示框
     *          2. 任何资源都必须弹出下载提示框
     *          3. 使用响应头设置资源的打开方式
     *              content-disposition：attachment;filename=xxx;
     */
}
