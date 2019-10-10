package com.nsoft.gkzp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * springboot入口
 * MapperScan("com.nsoft.gkzp.**.dao")为扫描mapper, 所以dao下面的类就不需要添加@mapper注解了
 * ServletComponentScan  添加了过滤器,故这里要添加@ServletComponentScan注解，spring才会扫描到过滤器（eg:com.nsoft.gkzp.syscore.config.filter.CorsFilter)
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.nsoft.gkzp.**.dao")
public class GzyGkzpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzyGkzpApplication.class, args);
    }


    /**
     * 在springboot整合vue前端时，vue使用url跳转时报404错误，此处代码解决此问题
     * 参照https://blog.csdn.net/Mr_EvanChen/article/details/83625082
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return factory -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
            factory.addErrorPages(error404Page);
        };
    }

}
