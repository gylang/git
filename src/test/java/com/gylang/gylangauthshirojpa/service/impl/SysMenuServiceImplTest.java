package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.domian.SysMenu;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysMenuService;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/17 17:28,
 * @DESC
 */
class SysMenuServiceImplTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    void findByUserId() {

        System.out.println(JsonUtils.obj2Json(sysMenuService.findByUserId(new Long(22))));
    }

    @Test
    void findTree() {

        System.out.println(JsonUtils.obj2Json(
                sysMenuService.findTree(new Long(22),1)
        ));
    }

    @Test
    void findByIdIn() {

        System.out.println(sysMenuService.findByIdIn(Arrays.asList((long)156155)));
    }

    @Test
    void findById() {
        System.out.println(sysMenuService.findById((long)156155));
    }

    @Test
    @Transactional
    void save() {

        System.out.println(sysMenuService.save(new SysMenu()));
    }

    @Test
    void findPage() {

        System.out.println(sysMenuService.findPage(new PageForm()));
    }

    @Test
    @Transactional
    void delete() {
        System.out.println(sysMenuService.delete(new SysMenu()));
    }

    @Test
    void findAll() {

        System.out.println(sysMenuService.findAll());
    }

    @Test
    @Transactional
    void delete1() {

        SysMenu menu = new SysMenu();
        menu.setId((long)156155);
        System.out.println(sysMenuService.delete(Arrays.asList(menu)));
    }
}
