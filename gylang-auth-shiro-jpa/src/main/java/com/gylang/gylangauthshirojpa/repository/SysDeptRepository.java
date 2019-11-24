package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.domian.SysDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/19 22:10,
 * @DESC
 */
public interface SysDeptRepository extends JpaRepository<SysDept, Long>, JpaSpecificationExecutor<SysDept> {

    List<SysDept> findByIdIn(List<Long> ids);
}
