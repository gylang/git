package com.gylang.gylangauthshirojpa.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author gylang,
 * @data 2019/11/16 10:11,
 * @DESC
 */
@Data
@Component
@ConfigurationProperties(prefix = "login.user")
public class LoginUseraBeanConfig {
    private int maxNum;
    private String sessionPrefix;
    private String user2SeesionPrefix;

}
