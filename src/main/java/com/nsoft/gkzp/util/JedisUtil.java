
package com.nsoft.gkzp.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class JedisUtil {
    public Jedis init(){
        //连接redis服务器(在这里是连接本地的)
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //权限认证
        jedis.auth("123456");
        return jedis;
    }
}

