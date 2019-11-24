package com.gylang.gylangauthshirojpa.exception.domian;

import com.gylang.gylangauthshirojpa.enums.BaseEnum;

/**
 * @author gylang,
 * @data 2019/11/17 17:18,
 * @DESC
 */
public class AuthException extends RuntimeException {

    private Integer code;
    private String msg;

    public AuthException(BaseEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }


    public AuthException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

