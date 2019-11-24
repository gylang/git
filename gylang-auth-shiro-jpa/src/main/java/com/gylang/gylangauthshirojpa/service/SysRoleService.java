package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.domian.SysRole;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/17 16:34,
 * @DESC
 */
public interface SysRoleService  extends BaseService<SysRole>{

    List<SysRole> findByUserId(Long userId);

    List<SysRole> findByIdIn(List<Long> idList);
}
