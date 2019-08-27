package com.nsoft.gkzp.syscore.config.component;

import com.nsoft.gkzp.system.sysuser.controller.SysUserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 拦截器
 * 登录检查
 */
@Component
public class LoginHandlerInterceptor  implements HandlerInterceptor {
    final private static Logger logger = LogManager.getLogger(LoginHandlerInterceptor.class);
    final private String ERRORMESSAGE = "errorMessage";
    final private String LOGINPAGE = "login.jsp";
    /**
     * 在Controller请求之前被调用
     * @return   true 放行   false 忽略当前请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Thread t = Thread.currentThread();
        logger.info("***********************************拦截器111-"+t.getName()+"*************************");
        //ResourceHttpRequest  访问资源文件时不验证 WebContext的存在
           if( handler instanceof ResourceHttpRequestHandler){
               return true;
           }
        logger.info("***********************************拦截器222-"+handler.toString()+"*************************");
           if( handler instanceof SysUserController){
            return true;
        }

        logger.info("***********************************拦截器333-"+t.getName()+"*************************");

        Object user = request.getSession().getAttribute("sysUser");

        if (user == null) {// 如果获取的request的session中的loginUser参数为空（未登录），就返回登录页，否则放行访问
            // 未登录，给出错误信息，
            request.setAttribute("msg","无权限请先登录");
            // 获取request返回页面到登录页
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
    /**
     * 在Controller请求之后被调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /**
     * 在DispatcherServlet完全处理完请求后被调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
