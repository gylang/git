package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author gylang,
 * @data 2019/11/17 16:43,
 * @DESC
 */
class SysRoleMenuRepositoryTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Test
    public void findByRoleId() {

        System.out.println(JsonUtils.obj2Json(sysRoleMenuRepository.findByRoleId(new Long(8))));
    }
}
