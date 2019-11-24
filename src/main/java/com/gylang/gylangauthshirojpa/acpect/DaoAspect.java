package com.gylang.gylangauthshirojpa.acpect;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import com.gylang.gylangauthshirojpa.utils.ValueValidUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author gylang,
 * @data 2019/11/18 10:44,
 * @DESC 切面动态添加用户信息修改的操作人
 */
@Aspect
@Component
public class DaoAspect {
    private static final String createBy = "createBy";
    private static final String createTime = "createTime";
    private static final String lastUpdateBy = "lastUpdateBy";
    private static final String lastUpdateTime = "lastUpdateTime";

    @Pointcut("execution(public * com.gylang.gylangauthshirojpa.controller.*.save*(..))")
    public void doSave() {

    }

    @Pointcut("execution(public * com.gylang.gylangauthshirojpa.controller.*.update*(..))")
    public void doUpdate() {
        System.out.println("修改方法参数");
    }

    @Around("doSave()")
    public Object addOperatorInfo1(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("插入记录");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return pjp.proceed();
        }
        Object[] objects = pjp.getArgs();
        if (objects != null && objects.length > 0) {
            LoginInfoDTO loginInfoDTO = getLoginInfo();
            if (null != loginInfoDTO && loginInfoDTO.getName() != null) {
                for (Object arg : objects) {
                    // 添加创建人 创建时间
                    if (ValueValidUtils.isBlank(BeanUtils.getProperty(arg, createBy))) {
                        BeanUtils.setProperty(arg, createBy, loginInfoDTO.getName());
                    }
                    if (ValueValidUtils.isBlank(BeanUtils.getProperty(arg, createTime))) {
                        BeanUtils.setProperty(arg, createTime, new Date());
                    }
                }
            }
        }
        Object object = pjp.proceed();
        return object;
    }

    @Around("doUpdate()")
    public Object doAroundCreate(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("更新记录");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return pjp.proceed();
        }
        Object[] objects = pjp.getArgs();
        if (objects != null && objects.length > 0) {
            LoginInfoDTO loginInfoDTO = getLoginInfo();
            for (Object arg : objects) {
                BeanUtils.setProperty(arg, lastUpdateBy, loginInfoDTO.getName());
                BeanUtils.setProperty(arg, lastUpdateTime, new Date());
            }
        }
        Object object = pjp.proceed();
        return object;

    }

    //
    private LoginInfoDTO getLoginInfo() {
        LoginInfoDTO loginInfoDTO = (LoginInfoDTO) SecurityUtils.getSubject().getPrincipal();
        return loginInfoDTO;
    }

    //

}
