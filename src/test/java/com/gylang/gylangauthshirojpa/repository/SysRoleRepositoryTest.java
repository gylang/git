package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author gylang,
 * @data 2019/11/17 16:49,
 * @DESC
 */
class SysRoleRepositoryTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Test
    void findById() {
        System.out.println(JsonUtils.obj2Json(sysRoleRepository.findById(new Long(1)).orElse(null)));
    }
}
