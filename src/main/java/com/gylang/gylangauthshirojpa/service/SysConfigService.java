package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.domian.SysConfig;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/21 12:59,
 * @DESC
 */
public interface SysConfigService extends BaseService<SysConfig> {

    List<SysConfig> findByIdIn(List<Long> ids);
}
