package com.gylang.gylangauthshirojpa.exception;

import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.enums.AuthEnum;
import com.gylang.gylangauthshirojpa.enums.LoginEnum;
import com.gylang.gylangauthshirojpa.enums.ResultEnum;
import com.gylang.gylangauthshirojpa.exception.domian.LoginException;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gylang,
 * @data 2019/11/16 9:26,
 * @DESC
 */
@RestControllerAdvice
public class GylangAuthExceptionHandler {

    @ExceptionHandler(value = IncorrectCredentialsException.class)
    @ResponseBody
    public Result handlerIncorrectCredentialsException(IncorrectCredentialsException e) {

        return Result.failure(LoginEnum.PASSWD_ERROR);
    }

    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public Result handlerLoginException(LoginException e) {

        return Result.failure(e.getCode(), e.getMsg());
    }

    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    @ResponseBody
    public Result unAuthException() {

        return Result.failure(AuthEnum.NO_RIGHT);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Result authenticationException() {
        return Result.failure(AuthEnum.NO_LOGIN);
    }


    //todo 捕获请求参数报错 -- json格式措出错
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        System.out.println("[参数错误] : " + JsonUtils.obj2Json(e.getMessage()));
        return Result.failure(ResultEnum.PARAMS_TYPE_ERROR);
    } @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public Result handlerNumberFormatException(NumberFormatException e) {

        System.out.println("[参数错误] : " + JsonUtils.obj2Json(e.getMessage()));
        return Result.failure(ResultEnum.PARAMS_TYPE_ERROR.getCode(), ResultEnum.PARAMS_TYPE_ERROR.getMsg() + e.getMessage());
    }

    //todo 捕获请求参数报错 -- 参数不全 / 参数错误
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<String> errorMsg = e.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        System.out.println("[参数错误] : " + JsonUtils.obj2Json(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));

        return Result.failure(ResultEnum.PARAMS_ERROR.getCode(), JsonUtils.obj2Json(errorMsg));
    }

//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Result handlerCarWashException(Exception e) {
//
//        return Result.failure(-1, "未知错误");
//    }

}
