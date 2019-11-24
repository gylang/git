package com.gylang.gylangauthshirojpa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gylang,
 * @data 2019/11/19 15:29,
 * @DESC 130
 */
@Getter
@AllArgsConstructor
public enum  UserEnum implements BaseEnum {

    NO_USER(11001,"当前用户不存在"),
    ;
    private Integer code;
    private String msg;
}
