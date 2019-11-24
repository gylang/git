package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/17 16:53,
 * @DESC
 */
class SysUserRoleRepositoryTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Test
    void findByUserId() {

        System.out.println(JsonUtils.obj2Json(sysUserRoleRepository.findByUserId(new Long(8))));
    }

}
