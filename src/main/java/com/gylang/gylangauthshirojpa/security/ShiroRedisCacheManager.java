package com.gylang.gylangauthshirojpa.security;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author gylang,
 * @data 2019/11/16 18:52,
 * @DESC
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {

    private RedisTemplate<byte[],byte[]> redisTemplate;
    private String prefix = "shiro_redis";

    public ShiroRedisCacheManager(RedisTemplate redisTemplate, String prefix){
        this.redisTemplate = redisTemplate;
        this.prefix = prefix;
    }
    //为了个性化配置redis存储时的key，我们选择了加前缀的方式，所以写了一个带名字及redis操作的构造函数的Cache类
    @Override
    protected Cache createCache(String name) throws CacheException {
        return new ShiroRedisCache(redisTemplate,prefix);
    }
}
