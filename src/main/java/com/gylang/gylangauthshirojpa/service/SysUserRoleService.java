package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.domian.SysUserRole;

import java.util.List;
import java.util.Set;

/**
 * @author gylang,
 * @data 2019/11/17 16:59,
 * @DESC
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {

    List<SysUserRole> findByUserId(Long userId);


}
