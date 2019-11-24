package com.gylang.gylangauthshirojpa.domian;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.service.SysUserService;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gylang,
 * @data 2019/11/18 9:46,
 * @DESC
 */

class SysUserTest extends GylangAuthShiroJpaApplicationTests {
    @Autowired
    private SysUserService sysUserService;

    @Test
    void testNotBlank() {

        SysUser sysUser = sysUserService.findByUserName("admin");
        System.out.println(JsonUtils.obj2Json(sysUser));
        sysUser.setName(null);
        System.out.println(JsonUtils.obj2Json(sysUser));
    }
}
