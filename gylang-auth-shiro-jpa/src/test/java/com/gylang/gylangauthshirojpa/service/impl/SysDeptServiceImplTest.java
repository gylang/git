package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.domian.SysDept;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysDeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/19 22:51,
 * @DESC
 */

class SysDeptServiceImplTest extends GylangAuthShiroJpaApplicationTests {
    @Autowired
    private SysDeptService sysDeptService;

    @Test
    void findById() {

        System.out.println(sysDeptService.findById((long) 1));
    }

    @Test
    @Transactional
    void save() {

        System.out.println(sysDeptService.save(new SysDept()));
    }

    @Test
    void findPage() {

        System.out.println(sysDeptService.findPage(new PageForm()));

    }

    @Test
    @Transactional
    void delete() {

        System.out.println(sysDeptService.delete(new SysDept()));
    }

    @Test
    @Transactional
    void delete1() {

        SysDept dept = new SysDept();
        dept.setId((long)1);
        System.out.println(sysDeptService.delete(Arrays.asList(dept)));
    }
}
