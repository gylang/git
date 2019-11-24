package com.gylang.gylangauthshirojpa.config;

import com.gylang.gylangauthshirojpa.config.domain.RedisBeanConfig;
import com.gylang.gylangauthshirojpa.domian.SysMenu;
import com.gylang.gylangauthshirojpa.security.*;
import com.gylang.gylangauthshirojpa.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Autowired
    private LoginUseraBeanConfig loginUseraBeanConfig;
    @Autowired
    private RedisBeanConfig redisBeanConfig;
    @Autowired
    private SysMenuService sysMenuService;

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //前后端分离 可以重定向来返回错误信息
        shiroFilterFactoryBean.setLoginUrl("/tick/not/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/tick/not/right");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<SysMenu> sysMenuList = sysMenuService.findAll();
        // todo 获取菜单列表 设置shiro 权限拦截
        for (SysMenu menu : sysMenuList) {
            if (!StringUtils.isEmpty(menu.getUrl()) && !StringUtils.isEmpty(menu.getPerms())) {
                if ("anon".equals(menu.getPerms()) || "authc".equals(menu.getPerms())) {
                    filterChainDefinitionMap.put(menu.getUrl(), menu.getPerms());
                } else {
                    filterChainDefinitionMap.put(menu.getUrl(), "perms[" + menu.getPerms() + "]");
                }
            }
        }
        filterChainDefinitionMap.put("/tick/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {


        System.out.println("[securityManager] 启动");
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(sysUserRealm());
        defaultSecurityManager.setCacheManager(redisCacheManager());
        defaultSecurityManager.setSessionManager(sessionManager());
        return defaultSecurityManager;
    }

    @Bean
    public SysUserRealm sysUserRealm() {

        SysUserRealm sysUserRealm = new SysUserRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");  //散列算法
        credentialsMatcher.setHashIterations(1);  //散列次数
        sysUserRealm.setCredentialsMatcher(credentialsMatcher);
        return sysUserRealm;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
//    @Bean
//    @DependsOn({"lifecycleBeanPostProcessor"})
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(RedisTemplate<Object, Object> template) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
//        return authorizationAttributeSourceAdvisor;
//    }

    /*----------------------------------  shiro -redis -------------------------------------*/


    //    private ShiroRedisCacheManager cacheManager(RedisTemplate template, String sessionprefix) {
//
//
//        return new ShiroRedisCacheManager(template, sessionprefix);
//    }
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        MyRedisSessionDAO redisSessionDAO = new MyRedisSessionDAO();
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setKeyPrefix(loginUseraBeanConfig.getSessionPrefix());
        return redisSessionDAO;
    }

    //
    public RedisCacheManager redisCacheManager() {

        RedisCacheManager redisCacheManager = new RedisCacheManager();
        ;
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    public StatelessSessionManager sessionManager() {
        StatelessSessionManager sessionManager = new StatelessSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    //
    public RedisManager redisManager() {

//        System.out.println(JsonUtils.obj2Json(redisBeanConfig));
        System.out.println(host + password + port);
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisBeanConfig.getHost());
        redisManager.setPassword(redisBeanConfig.getPassword());
        redisManager.setTimeout(200);
        return redisManager;
    }
}
