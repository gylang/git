package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gylang,
 * @data 2019/11/17 16:41,
 * @DESC
 */
class SysUserRepositoryTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Test
    public void  findByName() {

        System.out.println(sysUserRepository.findByName("admin"));
    }
}
