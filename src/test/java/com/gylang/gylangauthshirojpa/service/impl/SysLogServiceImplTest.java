package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import com.gylang.gylangauthshirojpa.domian.SysLog;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gylang,
 * @data 2019/11/21 16:21,
 * @DESC
 */
class SysLogServiceImplTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private SysLogService sysLogService;


    @Test
    void findById() {

        System.out.println(sysLogService.findById((long)1));
    }

    @Test
    void findByIdIn() {

        System.out.println(sysLogService.findByIdIn(Arrays.asList((long)1)));
    }

    @Test
    @Transactional
    void save() {
        SysLog sysLog = new SysLog();
        sysLog.setTime(System.currentTimeMillis());
        System.out.println(sysLogService.save(sysLog));
    }

    @Test
    void findPage() {

        System.out.println(sysLogService.findPage(new PageForm()));
    }

    @Test
    @Transactional
    void delete() {

        System.out.println(sysLogService.delete(new SysLog()));
    }

    @Test
    @Transactional
    void delete1() {

        SysLog sysLog = new SysLog();
        sysLog.setId((long)16516);
        System.out.println(sysLogService.delete(Arrays.asList(sysLog)));
    }
}
