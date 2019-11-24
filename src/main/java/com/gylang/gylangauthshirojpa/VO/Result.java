package com.gylang.gylangauthshirojpa.VO;

import com.gylang.gylangauthshirojpa.enums.BaseEnum;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    @Override
    public String toString() {
        return JsonUtils.obj2Json(this);
    }

    public Result() {
    }

    public Result(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data, 200, "返回成功");
    }

    public static <T> Result<T> failure() {
        return new Result<T>(-1, "系统错误");
    }

    public static <T> Result<T> failure(BaseEnum baseEnum) {
        return new Result<T>(baseEnum.getCode(), baseEnum.getMsg());
    }

    public static <T> Result<T> failure(int code, String msg) {
        return new Result<T>(code, msg);
    }

    public static <T> Result<T> auto(boolean result) {

        if (result) {
            return new Result<T>(200, "成功");
        } else {
            return new Result<T>(-1, "失败");
        }
    }

}
