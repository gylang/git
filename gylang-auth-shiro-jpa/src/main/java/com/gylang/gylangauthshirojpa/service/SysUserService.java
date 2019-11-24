package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysUser;
import com.gylang.gylangauthshirojpa.domian.SysUserRole;

import java.util.List;
import java.util.Set;

/**
 * @author gylang,
 * @data 2019/11/16 9:36,
 * @DESC
 */
public interface SysUserService extends BaseService<SysUser>{


    SysUser findByUserName(String userName);

    List<SysUser> findByIdIn(List<Long> sysUserIdList);

    Set<String> findPermit(Long id);

    Set<String> findAllPermit();

    List<SysUserRole> findUserRoles(Long userId);


}
