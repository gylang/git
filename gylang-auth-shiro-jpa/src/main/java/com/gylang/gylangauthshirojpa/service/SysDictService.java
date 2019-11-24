package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.domian.SysDict;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/21 12:29,
 * @DESC
 */
public interface SysDictService extends BaseService<SysDict> {

    List<SysDict> findByIdIn(List<Long> ids);
}
