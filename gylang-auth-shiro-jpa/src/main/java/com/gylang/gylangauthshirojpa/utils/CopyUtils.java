package com.gylang.gylangauthshirojpa.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.util.*;

public class CopyUtils {

    private static Set<String> getNullPropertyNames(Object source) {

        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (StringUtils.isEmpty(srcValue))
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames;
    }

//    public static void copyProperties(Object src, Object target) {
//        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
//    }

    public static void notNullAndOtherCopy(Object src, Object target, List<String> ignoreProperties) {
        List<String> ignore = new ArrayList<>();
        ignore.addAll(ignoreProperties);
        ignore.addAll(getNullPropertyNames(src));
        String[] result = new String[ignore.size()];
        BeanUtils.copyProperties(src, target,ignore.toArray(result));

    }
    public static void notNullCopy(Object src, Object target) {
        Set<String> ignoreProperties = getNullPropertyNames(src);
        String[] result = new String[ignoreProperties.size()];
        BeanUtils.copyProperties(src, target,ignoreProperties.toArray(result));

    }

    public static void haveNullCopy(Object src, Object target) {
        BeanUtils.copyProperties(src, target);

    }
}
