package ssm.lcx.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    /**
     * preHandel 叫做预处理方法
     * 特点：
     *  1. 方法在控制器方法（MyController 的 doSome）之前先执行的
     *      用户的请求首先到达的方法
     *  2. 在这个方法中可以获取请求的信息，验证请求是否符合要求
     *      可以验证用户是否有权限连接某个地址
     *      如果验证失败，可以截断请求，请求不能被处理
     *      如果验证成功，可以放行请求，此时控制器方法才能执行。
     * @param handler 被拦截的控制器对象
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return true;  // 为 true 放行，为 false 不放行
    }


    /**
     * 后处理方法
     *
     * 特点：在 Controller 的doSome 方法后执行的，能够获取到处理器方法的返回值
     *      ModeAndView，可以修改ModeAndView中的数据和视图，可以影响到最后执行的结果
     *      主要是对结果做二次修正
     *
     * @param request
     * @param response
     * @param handler   被拦截器执行的的 Controller 对象
     * @param modelAndView  处理方法的返回值
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    /**
     * 最后执行的方法
     *
     * 特点：
     * 1、在请求处理完成后执行。框架中当视图处理完成后，对视图执行了forward 。就认为请求处理完成。
     * 2、一般做资源回收工作，把占用的内存回收
     * @param request
     * @param response
     * @param handler   被拦截的Controller 处理对象
     * @param ex        程序中发生的异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
