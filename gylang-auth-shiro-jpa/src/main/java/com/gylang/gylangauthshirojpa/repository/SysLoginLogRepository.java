package com.gylang.gylangauthshirojpa.repository;

import com.gylang.gylangauthshirojpa.domian.SysLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/21 22:08,
 * @DESC
 */
public interface SysLoginLogRepository extends JpaRepository<SysLoginLog, Long>, JpaSpecificationExecutor<SysLoginLog> {

    List<SysLoginLog> findByIdIn(List<Long> ids);
}
