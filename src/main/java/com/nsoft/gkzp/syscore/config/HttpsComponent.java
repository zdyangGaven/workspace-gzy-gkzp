//package com.nsoft.gkzp.syscore.config;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
///**
// * 监听http端口，如访问网址为http协议的，自动转换为Https
// */
//@Configuration
//@PropertySource(value="classpath:application.properties")
//public class HttpsComponent {
//
//    //读取application.properties配置文件配置的https访问端口号
//    @Value("${server.port}")
//    public  int SYSTEM_HTTPS_PORT;
//    //读取application.properties配置文件配置的http监控端口（自动转换为https）
//    @Value("${server.http.port}")
//    public  int SYSTEM_HTTP_PORT;
//
//    @Bean
//    public Connector connector(){
//
//        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(SYSTEM_HTTP_PORT);//Connector监听的http的端口号
//        connector.setSecure(false);
//        connector.setRedirectPort(SYSTEM_HTTPS_PORT);//监听到http的端口号后转向到的https的端口号(一般会用443端口)
//        return connector;
//    }
//
//    @Bean
//    public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
//
//        TomcatServletWebServerFactory tomcat =new TomcatServletWebServerFactory(){
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint=new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection=new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(connector());
//        return tomcat;
//    }
//}
