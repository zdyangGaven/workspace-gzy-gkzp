package com.nsoft.gkzp.syscore.config;

import com.nsoft.gkzp.syscore.config.component.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 配置文件  注册
 *
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    @Autowired
    LoginHandlerInterceptor loginHandlerInterceptor;



    /**
     *  注册拦截器 ，我们自己写好的拦截器需要通过这里添加注册才能生效 (addPathPatterns 用来设置拦截路径，excludePathPatterns 用来设置白名单)
     *  LoginHandlerInterceptor 拦截器-登录检测
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       //这里添加多个拦截器
        // 登录检测        添加拦截的请求，并排除几个不拦截的请求  ----- 登录检测拦截规则:  /**     排除： 登录、注册相关页
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**")
                .excludePathPatterns( "/user/login","/user/register","/user/getUsers");

    }


    /**
     * 配置不需要经过controller就跳转到登录页面
     * Url重定向 (直接添加 registry.addViewController即可)
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("login");//浏览器发送 / 请求，同样展示login页面，但是不读取数据

    }


    /***
     * addResourceLocations指的是文件放置的目录，addResoureHandler指的是对外暴露的访问路径
     * (即这个方法是用来配置静态资源的，比如html，js，css，等等)
     */
    @Override
    public void addResourceHandlers(   ResourceHandlerRegistry  registry) {
        // TODO Auto-generated method stub
        //排除静态资源拦截
//        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
    }



}
