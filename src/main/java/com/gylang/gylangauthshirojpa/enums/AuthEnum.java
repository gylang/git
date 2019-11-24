package com.gylang.gylangauthshirojpa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gylang,
 * @data 2019/11/16 12:50,
 * @DESC
 * 授权错误 110 XX
 */
@Getter
@AllArgsConstructor
public enum  AuthEnum implements BaseEnum {

    NO_RIGHT(11001,"无权访问"),
    NO_LOGIN(11002,"登录已过期/未登录"),
    NO_ROLE(11003,"当前用户没有角色"),
    LOGIN_INFO_ERROR(11003,"登录信息出错"),
    ;
    private Integer code;
    private String msg;
}
