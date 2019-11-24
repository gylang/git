package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.domian.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/17 16:54,
 * @DESC
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, Long>, JpaSpecificationExecutor<SysMenu> {

    List<SysMenu> findByIdIn(Collection<Long> IdList);
}
