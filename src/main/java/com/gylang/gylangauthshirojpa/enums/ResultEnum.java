package com.gylang.gylangauthshirojpa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gylang,
 * @data 2019/11/18 9:13,
 * @DESC 120 XX
 */
@Getter
@AllArgsConstructor
public enum  ResultEnum implements BaseEnum {

   PARAMS_ERROR(12001, "参数错误"),
   PARAMS_TYPE_ERROR(12001, "参数格式错误"),
   USER_HAVE_REGISTER(12002, "用户名已经被注册"),
   ID_NULL(12003, "id为空"),
   RESULT_EMPTY(12003, "查询不到相关的数据"),

    ;
    private Integer code;
    private String msg;

}

