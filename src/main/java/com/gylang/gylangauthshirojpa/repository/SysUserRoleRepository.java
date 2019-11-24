package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.domian.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/17 16:39,
 * @DESC
 */
public interface SysUserRoleRepository extends JpaRepository<SysUserRole, Long> {

    List<SysUserRole> findByUserId(Long userId);
}
