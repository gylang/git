package com.gylang.gylangauthshirojpa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录错误, code前缀100 XX
 */
@Getter
@AllArgsConstructor
public enum LoginEnum implements BaseEnum {

    PASSWD_ERROR(10001, "用户名密码错误"),
    USER_STOP(10002, "账号已冻结"),
    USER_NOT_FOUND(10003, "账号不存在"),
    LOGIN_ERROR(10004, "登录失败"),
    LOGOUT_ERROR(10005, "登出失败"),

    ;
    private Integer code;
    private String msg;

}
