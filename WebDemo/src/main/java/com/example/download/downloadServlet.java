package com.example.download;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class downloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 中文文件名乱码问题
         *      1. 获取客户端使用的浏览器版本信息
         *      2. 根据不同的版本信息，设置filename的编码方式
         *      引入工具类方法来编码
         *
         */

        // 1. 获取请求参数
        String filename = req.getParameter("filename");
        // 2. 使用字节输入流加载进内存
        // 2.1 找到文件真实路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/" + filename);
        // 2.2 使用字节流关联
        FileInputStream fileInputStream = new FileInputStream(realPath);

        String mimeType = servletContext.getMimeType(filename);
        System.out.println(mimeType);
        System.out.println(filename);
        resp.setHeader("content-type",mimeType);

        resp.setHeader("content-disposition","attachment;filename=" + filename);

        // 3. 将输入流的数据写出到输出流中
        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] buff = new byte[1024*8];
        int len = 0;
        while((len = fileInputStream.read(buff))!=-1){
            outputStream.write(buff,0,len);
        }
        fileInputStream.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);

    }
}
