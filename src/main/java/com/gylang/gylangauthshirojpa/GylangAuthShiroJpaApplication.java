package com.gylang.gylangauthshirojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCaching
public class GylangAuthShiroJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GylangAuthShiroJpaApplication.class, args);
    }

}
