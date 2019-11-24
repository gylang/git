package com.gylang.gylangauthshirojpa.config;

import com.gylang.gylangauthshirojpa.security.OnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author gylang,
 * @data 2019/11/16 12:12,
 * @DESC
 */
@Configuration
public class OnlineUserConfig {


    @Autowired
    private LoginUseraBeanConfig loginUseraBeanConfig;



    @Bean
    public OnlineUserService userLoginQueue(RedisTemplate<String, String> redisTemplate) {

        OnlineUserService onlineUserService = new OnlineUserService();
        onlineUserService.setRedisTemplate(redisTemplate);
        onlineUserService.setMaxLive(loginUseraBeanConfig.getMaxNum());
        onlineUserService.setSessionPrefix(loginUseraBeanConfig.getSessionPrefix());
        onlineUserService.setUser2SeesionPrefix(loginUseraBeanConfig.getUser2SeesionPrefix());
        System.out.println("onlineUserService = " + onlineUserService);
        return onlineUserService;
    }
}
