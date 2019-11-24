package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.domian.SysMenu;
import com.gylang.gylangauthshirojpa.domian.SysRoleMenu;

import java.util.Collection;
import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/17 16:35,
 * @DESC
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu> {

    List<SysRoleMenu> findByRoleId(Long roleId);

    List<SysRoleMenu> findByRoleId(Collection<Long> roleId);

    void deleteByMenuIdIn(List<Long> menuId);

    void deleteByRoleIdIn(List<Long> roleId);
}
