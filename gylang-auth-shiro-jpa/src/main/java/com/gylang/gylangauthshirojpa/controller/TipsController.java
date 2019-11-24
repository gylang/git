package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.enums.AuthEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gylang,
 * @data 2019/11/16 12:48,
 * @DESC
 */
@RestController
@RequestMapping("tick")
public class TipsController {

    @GetMapping("/not/right")
    public Result notRight() {

        return Result.failure(AuthEnum.NO_RIGHT);
    }


    @GetMapping("/not/login")
    public Result notLogin() {

        return Result.failure(AuthEnum.NO_LOGIN);
    }
}
