package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.DTO.SysMenuDTO;
import com.gylang.gylangauthshirojpa.domian.SysMenu;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/16 13:01,
 * @DESC
 */
public interface SysMenuService extends BaseService<SysMenu> {

    List<SysMenu> findByUserId(Long userId);

    List<SysMenu> findTree(Long userId, int menuType);

    List<SysMenu> findByIdIn(List<Long> idList);

    List<SysMenu> findAll();
}
