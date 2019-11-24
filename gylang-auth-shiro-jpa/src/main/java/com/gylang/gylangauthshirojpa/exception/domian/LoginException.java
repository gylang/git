package com.gylang.gylangauthshirojpa.exception.domian;


import com.gylang.gylangauthshirojpa.enums.BaseEnum;

public class LoginException extends RuntimeException {

    private Integer code;
    private String msg;

    public LoginException(BaseEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }


    public LoginException(Integer code, String msg) {
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
