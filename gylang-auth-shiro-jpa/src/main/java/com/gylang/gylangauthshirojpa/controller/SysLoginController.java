package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.enums.LoginEnum;
import com.gylang.gylangauthshirojpa.form.LoginForm;
import com.gylang.gylangauthshirojpa.security.OnlineUserService;
import com.gylang.gylangauthshirojpa.service.SysUserService;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.validation.Valid;

/**
 * @author gylang,
 * @data 2019/11/16 9:32,
 * @DESC
 */
@RestController
@RequestMapping("comm")
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private OnlineUserService onlineUserService;

    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginForm loginForm) {
        System.out.println("账号" + JsonUtils.obj2Json(loginForm));
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginForm.getUserName(), loginForm.getPassword());
        System.out.println(loginForm);
        subject.login(usernamePasswordToken);
        if (subject.isAuthenticated()) {
            //登录成功 返回token 保存在线列表
            LoginInfoDTO loginInfoDTO = (LoginInfoDTO) subject.getPrincipal();
            String sessionId = SecurityUtils.getSubject().getSession().getId().toString();

            // todo 将当前登录用户保存到队列当中
            onlineUserService.putAndVerifyUserQueue(loginInfoDTO.getId(), sessionId);
            loginInfoDTO.setToken(sessionId);
            return Result.success(loginInfoDTO);
        } else {
            return Result.failure(LoginEnum.LOGIN_ERROR);
        }
    }

    @PostMapping("/logout")
    public Result logout() {

        Subject subject = SecurityUtils.getSubject();
        String sessionId = subject.getSession().getId().toString();
        System.out.println("sessionId :" + sessionId);
        LoginInfoDTO loginInfoDTO = (LoginInfoDTO) subject.getPrincipal();
        if (null == loginInfoDTO) {
            onlineUserService.removeSession(sessionId);
            return Result.failure(LoginEnum.LOGOUT_ERROR);
        }
        long userId = loginInfoDTO.getId();
        subject.logout();
        return Result.success("注销成功");


    }


}
