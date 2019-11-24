package com.gylang.gylangauthshirojpa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /* json 转对象 */
    public static <T> T str2Obj(String str, Class<T> clazz) {
        try {
            T t = objectMapper.readValue(str, clazz);
            return t;
        }  catch (IOException e) {
            System.out.println("[JsonUtils] : json 转object 失败");
            return null;
        }
    }

    /* 对象转json */
    public static String obj2Str(Object obj) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {

        }
        return result;
    }

    /* 对象转json */
    public static String obj2Json(Object obj) {
        String result = null;
        try {
            result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {

        }
        return result;
    }
}
