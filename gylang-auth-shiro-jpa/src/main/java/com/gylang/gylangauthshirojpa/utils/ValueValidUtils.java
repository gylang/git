package com.gylang.gylangauthshirojpa.utils;

import org.springframework.util.StringUtils;

/**
 * @author gylang,
 * @data 2019/11/18 10:14,
 * @DESC
 */


public class ValueValidUtils {


    public static boolean validUserName(String username) {

        // 设置账号检验规则
        if (StringUtils.isEmpty(username)
                || username.length() < 6
                || username.length() > 16) {

            return true;
        } else {
            return true;
        }
    }

    public static boolean isBlank(String value) {
        return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
    }
}
