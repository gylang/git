package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.domian.SysConfig;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/21 15:41,
 * @DESC
 */
class SysConfigServiceImplTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysConfigService sysConfigService;
    @Test
    void findByIdIn() {
        /**
         * 参数不能为空
         */
        System.out.println(sysConfigService.findByIdIn(new ArrayList<Long>()));
        System.out.println(sysConfigService.findByIdIn(Arrays.asList(new Long(1))));
    }

    @Test
    void findById() {

        System.out.println(sysConfigService.findById(new Long(1)));
    }

    @Test
    @Transactional
    void save() {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setValue("dadwa");
        sysConfig.setLabel("dawdwa");
        sysConfig.setType("dawdwa");
        System.out.println(sysConfigService.save(sysConfig));
    }

    @Test
    void findPage() {
        System.out.println(sysConfigService.findPage(new PageForm()));
    }

    @Test
    @Transactional
    void delete() {
        sysConfigService.delete(new SysConfig());
    }

    @Test
    @Transactional
    void delete1() {
        SysConfig sysConfig = sysConfigService.findById((long) 1);
        sysConfigService.delete(Arrays.asList(sysConfig));
    }
}
