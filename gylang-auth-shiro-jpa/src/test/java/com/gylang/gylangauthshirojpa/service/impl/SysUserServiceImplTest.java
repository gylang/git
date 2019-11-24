package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.domian.SysUser;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysUserService;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * @author gylang,
 * @data 2019/11/17 13:21,
 * @DESC
 */
public class SysUserServiceImplTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void findPage() {

        PageForm pageForm = new PageForm();
        pageForm.getOrderName().add("lastUpdateTime");
        pageForm.setOrderType(1);
        System.out.println(JsonUtils.obj2Json(sysUserService.findPage(pageForm)));
    }


    @Test
    public void testFindPermit() {

        System.out.println(sysUserService.findPermit(new Long(3)));
        System.out.println(sysUserService.findAllPermit());
    }

    @Test
    void findByUserName() {

        System.out.println(sysUserService.findByUserName("admin"));
    }

    @Test
    void findByIdIn() {

        System.out.println(sysUserService.findByIdIn(Arrays.asList((long) 1)));
    }

    @Test
    void findById() {

        System.out.println(sysUserService.findById((long) 1));
    }

    @Test
    @Transactional
    void save() {

        SysUser sysUser = new SysUser();
        sysUser.setName("daw");
        sysUser.setPassword("daw");
        System.out.println(sysUserService.save(sysUser));
    }


    @Test
    @Transactional
    void delete() {

        System.out.println(sysUserService.delete(new SysUser()));
    }


    @Test
    void findPermit() {
        System.out.println(sysUserService.findPermit((long) 3));
    }

    @Test
    void findAllPermit() {

        System.out.println(sysUserService.findAllPermit());
    }

    @Test
    void findUserRoles() {

        System.out.println(sysUserService.findUserRoles((long) 1));
    }

    @Test
    @Transactional
    void delete1() {
        SysUser sysUser = new SysUser();
        sysUser.setId((long) 1);
        System.out.println(sysUserService.delete(Arrays.asList(sysUser)));
    }
}
