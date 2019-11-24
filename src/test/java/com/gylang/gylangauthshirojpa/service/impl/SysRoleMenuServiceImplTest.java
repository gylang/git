package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.domian.SysRoleMenu;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysRoleMenuService;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/17 17:06,
 * @DESC
 */
class SysRoleMenuServiceImplTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Test
    void findById() {
        System.out.println(sysRoleMenuService.findById((long)1));
    }

    @Test
    @Transactional
    void save() {

        System.out.println(sysRoleMenuService.save(new SysRoleMenu()));
    }

    @Test
    void findPage() {

        System.out.println(sysRoleMenuService.findPage(new PageForm()));
    }

    @Test
    @Transactional
    void delete() {

        System.out.println(sysRoleMenuService.delete(new SysRoleMenu()));
    }

    @Test
    void findByRoleId() {

        System.out.println(JsonUtils.obj2Json(sysRoleMenuService.findByRoleId(new Long(2))));

    }
}
