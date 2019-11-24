package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.domian.SysRole;
import com.gylang.gylangauthshirojpa.domian.SysRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/17 16:37,
 * @DESC
 */
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenu, Long>, JpaSpecificationExecutor<SysRoleMenu> {

    List<SysRoleMenu> findByRoleId(Long roleId);
    List<SysRoleMenu> findByRoleIdIn(Collection<Long> roleId);
}
