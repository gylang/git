package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.config.LoginUseraBeanConfig;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.security.OnlineUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author gylang,
 * @data 2019/11/16 11:16,
 * @DESC
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private OnlineUserService onlineUserService;
    @Autowired
    private LoginUseraBeanConfig loginUseraBeanConfig;

    @RequiresPermissions("fafa")
    @GetMapping("/login")
    public void login() {

        long userId = 1;
        Random random = new Random();
        double session = random.nextGaussian();
        String sessionId = "session" + session;
        onlineUserService.putAndVerifyUserQueue(userId, sessionId);
    }

    @GetMapping("/page")
    public void getpage(PageForm pageForm) {

        System.out.println(pageForm.getPage());
    }

    @GetMapping("/info")
    public void getInfo() {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
    }


}
