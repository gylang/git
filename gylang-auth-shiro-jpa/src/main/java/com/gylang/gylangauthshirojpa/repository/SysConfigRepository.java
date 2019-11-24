package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.domian.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/21 13:02,
 * @DESC
 */
public interface SysConfigRepository extends JpaRepository<SysConfig, Long>, JpaSpecificationExecutor<SysConfig> {

    List<SysConfig> findByIdIn(List<Long> ids);

    @Query(value = "select * from sys_config where if(?1 !='',id=?1,1=0)", nativeQuery = true)
    List<SysConfig> findForId(Long id);

    @Query(value = "select * from sys_config where if(?1 !='',value like CONCAT('%',?1,'%'),1=0)", nativeQuery = true)
    List<SysConfig> findForValueLike(String value);
}
