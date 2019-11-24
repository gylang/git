package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/17 16:56,
 * @DESC
 */
class SysMenuRepositoryTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Test
    void findByIdIn() {
        List<Long> idList = Arrays.asList(new Long(1));
        System.out.println(JsonUtils.obj2Json(sysMenuRepository.findByIdIn(idList)));
    }
}
