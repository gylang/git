package com.gylang.gylangauthshirojpa.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Random;
import java.util.UUID;

/**
 * @author gylang,
 * @data 2019/11/18 10:03,
 * @DESC
 */
public class Md5Util {

    private static Random random = new Random();

    public static String passWdMd5(String content, String salt) {

        Md5Hash md5Hash = new Md5Hash(content, salt);
        return md5Hash.toString();
    }

    public static String generSalt() {

        String salt = UUID.randomUUID().toString()
                .replaceAll("-", "").substring(0, 20);
        return salt;
    }
}
