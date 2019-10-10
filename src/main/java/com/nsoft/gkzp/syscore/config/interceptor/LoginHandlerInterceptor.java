package com.nsoft.gkzp.syscore.config.interceptor;

import com.nsoft.gkzp.syscore.web.UserContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 拦截器
 * 登录检查
 */
@Component
public class LoginHandlerInterceptor  implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    UserContext userContext;

    final private static Logger logger = LogManager.getLogger(LoginHandlerInterceptor.class);
   // final private String ERRORMESSAGE = "errorMessage";
   // final private String LOGINPAGE = "login.jsp";

    /**
     * 在Controller请求之前被调用
     * @return   true 放行   false 忽略当前请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Thread t = Thread.currentThread();

        //ResourceHttpRequest  访问资源文件时不验证 WebContext的存在
           if( handler instanceof ResourceHttpRequestHandler){
               return true;
           }
        logger.info("***********************************拦截器111-"+handler.toString()+"*************************");


        Object user =  WebUtils.getSessionAttribute(request,"userContext");
        if (user == null) {// 如果获取的request的session中的loginUser参数为空（未登录），就返回登录页，否则放行访问
            response.sendError(401);//session超时,页面响应401错误
            return false;
        } else {
            userContext = (UserContext)user;
            //验证当前请求的session是否是已登录的session
            String loginSessionId = stringRedisTemplate.opsForValue().get("loginUser:" +userContext.getLoginUserId());
            if (loginSessionId == null || loginSessionId.equals(request.getSession().getId()))
            {
                stringRedisTemplate.opsForValue().set("loginUser:" +userContext.getLoginUserId(), loginSessionId,1, TimeUnit.HOURS);//相当于重新设置redis里存的有效时间
                return true;
            }else{
                response.sendError(401);//用户已在其他地方登录，请重新登录
                return false;
            }
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
