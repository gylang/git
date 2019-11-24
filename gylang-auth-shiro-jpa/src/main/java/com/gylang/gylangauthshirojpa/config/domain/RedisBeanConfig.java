package com.gylang.gylangauthshirojpa.config.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author gylang,
 * @data 2019/11/16 17:13,
 * @DESC
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisBeanConfig {

    private Integer port = 6379;
    private Long timeout = new Long(0);
    private String host = "127.0.0.1";
    private String password = "6615";


}
