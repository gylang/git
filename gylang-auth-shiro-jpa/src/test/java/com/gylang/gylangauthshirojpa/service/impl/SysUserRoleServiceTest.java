package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.domian.SysUserRole;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysUserRoleService;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/17 17:03,
 * @DESC
 */
public class SysUserRoleServiceTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Test
    public void findByUserId() {

        System.out.println(JsonUtils.obj2Json(sysUserRoleService.findByUserId(new Long(8))));
    }

    @Test
    public void findById() {

        System.out.println(sysUserRoleService.findById((long) 1));
    }

    @Test
    @Transactional
    public void save() {

        System.out.println(sysUserRoleService.save(new SysUserRole()));
    }

    @Test
    public void findPage() {

        System.out.println(sysUserRoleService.findPage(new PageForm()));
    }

    @Test
    @Transactional
    public void delete() {

        System.out.println(sysUserRoleService.delete(new SysUserRole()));
    }
}
