package com.gylang.gylangauthshirojpa.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class LoginInfoDTO implements Serializable {

    private Long id;

    private String name;

    private String nickName;

    private String avatar;

    private String email;

    private String mobile;

    private Long deptId;

    private String token;

//    private String password;

//    private String salt;

//    private Byte status;

//    private String createBy;
//
//    private Date createTime;
//
//    private String lastUpdateBy;
//
//    private Date lastUpdateTime;
//
//    private Byte delFlag;
}
