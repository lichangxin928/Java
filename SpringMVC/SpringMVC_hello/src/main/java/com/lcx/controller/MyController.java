package com.lcx.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller 创建处理器对象，对象放在springmvc 容器中
 * 位置：在类的上面
 * 和 spring 中是 @Component
 */
@Controller
public class MyController {
    /*
    * 处理用户提交的请求，springmvc 中是使用方法啦处理的
    * 方法是自定义的，可以有多种返回值，多种参数，方法名称自定义
    * */


    /**
     * 准备使用 dosome 来处理 some.do 的请求
     * @RequestMapping:请求映射，作用是把一个请求地址和一个方法绑定在一起，
     *                  一个请求指定要一个方法的处理
     *              属性：1. value 是一个string 表示请求的 URL 地址
     *                  value 必须是唯一的，不能重复。在使用 时推荐以 "/" 开头
     *              位置：1. 在方法上面
     *                   2. 在类的上面
     *          说明：使用 RequestMapping 修饰的方法叫做处理器方法或者控制器方法。
     *          使用 @RequestMapping 修饰的方法可以处理请求的，类似 Servlet 中的 doGet doPost
     *
     * 返回值:ModeLAndView
     *      Model：数据，请求处理完成后要显示给用户的数据
     *      view：视图，比如 jsp 等等。
     */
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(){        // doGet() --- service 请求处理
        //
        ModelAndView mv = new ModelAndView();
        // 添加数据,框架在请求的最后把数据放入到 Request 作用域中
        mv.addObject("msg","Hello World");
        mv.addObject("fun","执行的 doSome 方法");
        //指定视图
        // 框架对视图执行的 forward 操作，Request.getRequestDispatcher(...).forward()
        // mv.setViewName("/show.jsp");
        // 返回 mv
        // 当配置了视图解析器后，可以使用逻辑名称（文件名），指定视图
        // 框架会使用视图解析器的前缀 + 逻辑名称 + 后缀，组成完全路径，这里就是字符连接操作
        mv.setViewName("show");
        return mv;
    }
}
