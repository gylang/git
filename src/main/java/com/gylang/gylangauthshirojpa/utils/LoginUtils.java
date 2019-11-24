package com.gylang.gylangauthshirojpa.utils;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * @author gylang,
 * @data 2019/11/20 20:39,
 * @DESC
 */
public class LoginUtils {

    private static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static LoginInfoDTO getLoginInfo() {

        return (LoginInfoDTO) getSubject().getPrincipal();
    }

    public static Long getUserId() {

        LoginInfoDTO loginInfoDTO = getLoginInfo();
        if (null == loginInfoDTO) {
            return null;
        } else {
            return getLoginInfo().getId();
        }

    }
}

