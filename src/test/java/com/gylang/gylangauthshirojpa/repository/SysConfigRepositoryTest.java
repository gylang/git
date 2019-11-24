package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author gylang,
 * @data 2019/11/21 13:17,
 * @DESC
 */

public class SysConfigRepositoryTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Test
    public void testFindByIdIn(){

        System.out.println(JsonUtils.obj2Json(sysConfigRepository.findByIdIn(Arrays.asList(new Long(1)))));
    }

    @Test
    public void testFindById(){
        System.out.println(JsonUtils.obj2Json(sysConfigRepository.findById((long) 1).orElse(null)));

    }

    @Test
    public void testIfFunction(){

        System.out.println("记录值为3参数为null" + JsonUtils.obj2Str(sysConfigRepository.findForId(null)));
        System.out.println("记录值为3参数为2" + JsonUtils.obj2Str(sysConfigRepository.findForId((long) 2)));
        System.out.println("记录值为3参数为3" + JsonUtils.obj2Str(sysConfigRepository.findForId((long) 3)));
        System.out.println("记录值为#14889A参数为null" + JsonUtils.obj2Str(sysConfigRepository.findForValueLike(null)));
        System.out.println("记录值为#14889A参数为7" + JsonUtils.obj2Str(sysConfigRepository.findForValueLike("7")));
        System.out.println("记录值为#14889A参数为4" + JsonUtils.obj2Str(sysConfigRepository.findForValueLike("4")));

    }

}
