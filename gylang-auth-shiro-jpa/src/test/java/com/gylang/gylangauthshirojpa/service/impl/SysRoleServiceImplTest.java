package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysRole;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysRoleService;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/19 21:44,
 * @DESC
 */
class SysRoleServiceImplTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void findById() {

        System.out.println(sysRoleService.findById((long) 1));
    }

    @Test
    @Transactional
    public void save() {

        System.out.println(sysRoleService.save(new SysRole()));
    }

    @Test
    void findPage() {

        PageForm pageForm = new PageForm();
        pageForm.setParam("name", "");
        PageResult pageResult = sysRoleService.findPage(pageForm);
        System.out.println("分页查询 -- 角色 : " + JsonUtils.obj2Json(pageResult));
    }

    @Test
    @Transactional
    public void delete() {

        System.out.println(sysRoleService.delete(new SysRole()));
    }

    @Test
    @Transactional
    public void delete1() {

        SysRole sysRole = new SysRole();
        sysRole.setId((long)1);
        System.out.println(sysRoleService.delete(Arrays.asList(sysRole)));
    }

    @Test
    public void findByUserId() {

        System.out.println(JsonUtils.obj2Json(sysRoleService.findByUserId(new Long(1))));
    }


}
