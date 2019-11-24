package com.gylang.gylangauthshirojpa.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author gylang,
 * @data 2019/11/17 15:21,
 * @DESC
 */
@Data
public class LoginForm {

    @NotBlank(message = "账号不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
}
