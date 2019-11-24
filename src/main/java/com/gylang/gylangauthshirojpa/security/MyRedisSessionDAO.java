package com.gylang.gylangauthshirojpa.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gylang,
 * @data 2019/11/17 15:46,
 * @DESC
 */
public class MyRedisSessionDAO extends RedisSessionDAO {

    @Autowired
    private OnlineUserService onlineUserService;

    @Override
    public void update(Session session) throws UnknownSessionException {

        /**
         *  触发session更新 刷新用户在线登录列表过期时间
         */
        super.update(session);
        onlineUserService.updateOnlineExpire(session);

    }
}
