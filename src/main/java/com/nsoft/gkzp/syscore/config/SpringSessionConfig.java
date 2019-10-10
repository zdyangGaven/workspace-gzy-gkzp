package com.nsoft.gkzp.syscore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * https://www.cnblogs.com/hujinshui/p/11025848.html
 * spring-session 2.x 中 Cookie里面引入了SameSite他默认值是 Lax，
 * SameSite Cookie 是用来防止CSRF攻击，它有两个值：Strict、Lax
 * SameSite = Strict：意为严格模式，表明这个cookie在任何情况下都不可能作为第三方cookie；
 * SameSite = Lax：意为宽松模式，在get请求是可以作为第三方cookie，但是不能携带cookie进行跨域post访问
 * 总结：前端请求到后台，每次session都不一样，每次都是新的会话，导致获取不到用户信息
 */
@Configuration
public class SpringSessionConfig {

    public SpringSessionConfig() {
    }

    @Bean
    public CookieSerializer httpSessionIdResolver() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        // 取消仅限同一站点设置
        cookieSerializer.setSameSite(null);
        return cookieSerializer;
    }
}
