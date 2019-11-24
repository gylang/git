package com.gylang.gylangauthshirojpa.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author gylang,
 * @data 2019/11/18 8:57,
 * @DESC
 */
@Data
public class SysUserForm {

    private Long id;

    @NotBlank(message = "账号不能为空")
    private String name;

    private String nickName;

    private String avatar;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    private Byte status;

    private Long deptId;
}
