package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.domian.SysDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/21 12:28,
 * @DESC
 */
public interface SysDictRepository extends JpaRepository<SysDict, Long>, JpaSpecificationExecutor<SysDict> {

    List<SysDict> findByIdIn(List<Long> ids);
}
