//package com.gylang.gylangauthshirojpa.acpect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author gylang,
// * @data 2019/11/21 23:12,
// * @DESC
// */
//@Aspect
//@Component
//public class AllAspect {
//
//    @Pointcut("execution(public * com.gylang.gylangauthshirojpa.controller.*.*(..))")
//    public void all() {
//    }
//
//    @Before("all()")
//    public void all1() {
//
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        System.out.println(request.getMethod());
//        System.out.println(request.getParameterMap());
//    }
//}
