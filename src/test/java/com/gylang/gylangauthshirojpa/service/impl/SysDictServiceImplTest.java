package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.domian.SysDict;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysDictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/21 16:10,
 * @DESC
 */
class SysDictServiceImplTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysDictService sysDictService;

    @Test
    void findById() {

        System.out.println(sysDictService.findById((long) 1));
    }

    @Test
    void findByIdIn() {

        System.out.println(sysDictService.findByIdIn(Arrays.asList((long)1)));
    }

    @Test
    @Transactional
    void save() {
        SysDict dict = new SysDict();
        dict.setLabel("Daw");
        dict.setType("Daw");
        dict.setValue("Daw");
        System.out.println(sysDictService.save(dict));
    }

    @Test
    void findPage() {

        System.out.println(sysDictService.findPage(new PageForm()));
    }

    @Test
    @Transactional
    void delete() {

        System.out.println(sysDictService.delete(new SysDict()));
    }

    @Test
    @Transactional
    void delete1() {

        SysDict dict = new SysDict();
        dict.setId((long)1);
        System.out.println(sysDictService.delete(Arrays.asList(dict)));
    }
}
