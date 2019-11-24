package com.gylang.gylangauthshirojpa.security;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gylang,
 * 用户在线服务 用户从左边进 右边出 或者弹出特定用户
 */


@Slf4j
@Setter
public class OnlineUserService {


    private RedisTemplate redisTemplate;
    private int maxLive = 1;                                    //最大登录人数
    private String sessionPrefix = "shiro_redis_";             //session缓存的前缀
    private String user2SeesionPrefix = "user_session_id_";   //用户在线列表前缀

    /**
     * @param userId
     * @param sessionId 添加在线用户
     */
    private void putUserIn(long userId, String sessionId) {

        String key = getUserSessionKey(String.valueOf(userId));
        redisTemplate.opsForList().leftPush(key, sessionId);
        redisTemplate.expire(key, 1800, TimeUnit.SECONDS);
        log.info("[用户在线列表] : 在线列表{} : 用户进入在线队列{}", key, sessionId);
    }

    /**
     * @param userId
     * @param sessionId 踢出指定在线用户
     */
    public void removeUserOut(long userId, String sessionId) {

        String key = getUserSessionKey(String.valueOf(userId));
        redisTemplate.opsForList().remove(key, 0, sessionId);
        redisTemplate.delete(getSessionKey(sessionId));
        updateOnlineExpire(userId);


    }

    /**
     * 删除session
     *
     * @param sessionId
     */
    public void removeSession(String sessionId) {

        redisTemplate.delete(getSessionKey(sessionId));
        log.info("[删除session] : remove session{}", sessionId);

    }

    /**
     * 踢出在线用户列表最后一个 删除对应session 更新列表过期时间
     */
    public void removeFirstUserOut(long userId) {

        String key = getUserSessionKey(String.valueOf(userId));
        String removeUser = (String) redisTemplate.opsForList().rightPop(key);
        redisTemplate.delete(getSessionKey(removeUser));
        updateOnlineExpire(userId);

    }

    /**
     * 校验并添加用户
     */
    public void putAndVerifyUserQueue(long userId, String sessionId) {

        String key = getUserSessionKey(String.valueOf(userId));
        List<String> userSessionList = new ArrayList<>();
        userSessionList.addAll(redisTemplate.opsForList().range(key, 0, -1));

        for (String s : userSessionList) {
            if (s.equals(sessionId)) {
                //已经设备在线列表中已经存在
                return;
            }
        }
        // 判断在线用户是否已满 并分出一个空间给当前用户 并移除对应的用户数据
        int queueSize = userSessionList.size();
        while (queueSize > maxLive - 1) {
            queueSize = queueSize - 1;
            removeFirstUserOut(userId);
        }
        //添加用户到在线列表
        putUserIn(userId, sessionId);
    }

    public void updateOnlineExpire(Session session) {
        Subject subject = SecurityUtils.getSubject();
        LoginInfoDTO loginInfoDTO = (LoginInfoDTO) subject.getPrincipal();
        System.out.println("更新session : " + loginInfoDTO);
        if (null == loginInfoDTO) {
            return;
        }
        String key = getUserSessionKey(String.valueOf(loginInfoDTO.getId()));
        redisTemplate.expire(key, 1800, TimeUnit.SECONDS);
    }

    public void updateOnlineExpire(Long userId) {

        String key = getSessionKey(String.valueOf(userId));
        redisTemplate.expire(key, 1800, TimeUnit.SECONDS);
    }

    private String getSessionKey(String key) {

        return sessionPrefix + key;

    }

    private String getUserSessionKey(String key) {

        return user2SeesionPrefix + key;

    }

}
