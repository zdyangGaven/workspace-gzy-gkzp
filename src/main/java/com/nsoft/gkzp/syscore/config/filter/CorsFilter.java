package com.nsoft.gkzp.syscore.config.filter;

import com.nsoft.gkzp.syscore.config.MyDefinedUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "corsFilter")

public class CorsFilter implements Filter {

    final private static Logger logger = LogManager.getLogger(CorsFilter.class);

    @Autowired
    MyDefinedUtil myDefinedUtil;

    @Override
    public void destroy() {
    }

    /**
     * 此过滤器只是处理跨域问题
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String origin = request.getHeader("Origin");
        if(origin == null) {
            origin = request.getHeader("Referer");
        }
//        //允许跨域白名单
//        String[] whiteList = (myDefinedUtil.SYSTEM_ACCESSCONTROLALLOWORIGIN).split(",") ;
//        boolean isValid = false;
//        for(String ip : whiteList){//这里我设置本地访问（localhost，127.0.0.1）自动为白名单里的
//             if(origin != null && origin.equals(ip)){
//                isValid = true;
//                break;
//            }
//        }

        //允许跨域白名单
        String whiteList=myDefinedUtil.SYSTEM_ACCESSCONTROLALLOWORIGIN;
        boolean isValid = false;
         String temp    = null;
        if(origin != null){
            try {
                int a = origin.indexOf("://") + 3;
                temp = origin.substring(origin.indexOf("://") + 3);
                int b = temp.indexOf(":");
                if (b > 0) {
                    temp = temp.substring(0, b);
                }
                isValid = whiteList.contains(temp); //将origin截出ip字符串
            }catch (Exception e){
                logger.error("白名单校验出错:"+e.getMessage(),e);
            }
        }
        logger.info("跨域验证:origin="+origin+"***temp="+temp+"***isValid="+isValid);// 如为跨域请求，下面的"Access-Control-Allow-Origin"值置为null，就无法访问了。。。如果为非跨域请求，这个为null不会受影响，依然允许访问
        response.setHeader("Access-Control-Allow-Origin", isValid ? origin : origin);// 允许指定域访问跨域资源(这里不能写*，*代表接受所有域名访问，如写*则下面一行代码无效。谨记)
        response.setHeader("Access-Control-Allow-Credentials", "true");//true代表允许客户端携带cookie(此时origin值不能为“*”，只能为指定单一域名)
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH"); /// 允许浏览器在预检请求成功之后发送的实际请求方法名
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin, X-Requested-With, Content-Type, Accept,Access-Token");// 允许浏览器发送的请求消息头
        //response.setHeader("Access-Control-Max-Age", "86400");            // 浏览器缓存预检请求结果时间,单位:秒
        //logger.info("****************测试过滤器及日志1111");
        chain.doFilter(request,response);
        //logger.error("****************测试过滤器及日志2222");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


}
