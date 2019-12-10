package com.nsoft.gkzp;
/**
 * 如果使用外置tomcat，此为外置tomcat的启动类
 */

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //传入SpringBoot应用的主程序
        return application.sources(com.nsoft.gkzp.GzyGkzpApplication.class);
    }

}
