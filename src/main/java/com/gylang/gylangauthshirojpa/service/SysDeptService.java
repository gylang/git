package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.domian.SysDept;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/19 22:08,
 * @DESC
 */
public interface SysDeptService extends BaseService<SysDept> {

    List<SysDept> findByIdIn(List<Long> ids);
    List<SysDept> findTree();
}
