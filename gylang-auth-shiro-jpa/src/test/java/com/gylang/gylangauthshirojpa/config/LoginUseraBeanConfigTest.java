package com.gylang.gylangauthshirojpa.config;

import com.gylang.gylangauthshirojpa.GylangAuthShiroJpaApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gylang,
 * @data 2019/11/16 10:16,
 * @DESC
 */
public class LoginUseraBeanConfigTest extends GylangAuthShiroJpaApplicationTests {

    @Autowired
    private LoginUseraBeanConfig loginUseraBeanConfig;
    @Test
   public void getMaxNum() {
        System.out.println(loginUseraBeanConfig.toString());
    }
}
