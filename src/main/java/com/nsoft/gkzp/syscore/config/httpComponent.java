//package com.nsoft.gkzp.syscore.config;
//
//import org.apache.catalina.connector.Connector;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//
///**
// * 监听http端口，使http访问端口生效
// */
//@Configuration
//@PropertySource(value="classpath:application.properties")
//public class httpComponent {
//
//    //读取application.properties配置文件配置的http监控端口
//    @Value("${server.http.port}")
//    public  int SYSTEM_HTTP_PORT;
//
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addAdditionalTomcatConnectors(createStandardConnector()); // 添加http
//        return tomcat;
//    }
//
//    private Connector createStandardConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(SYSTEM_HTTP_PORT);
//        return connector;
//    }
//
//}
