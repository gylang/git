package com.gylang.gylangauthshirojpa.utils;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.ObjectUtils;


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
        Subject subject= getSubject();
        if (null == subject) {
            return null;
        }
        if (ObjectUtils.isEmpty(subject.getPrincipal())) {
            return null;
        }
        return (LoginInfoDTO) subject.getPrincipal();
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

