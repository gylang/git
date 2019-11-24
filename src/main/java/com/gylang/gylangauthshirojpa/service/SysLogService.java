package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysLog;
import com.gylang.gylangauthshirojpa.domian.SysLoginLog;
import com.gylang.gylangauthshirojpa.form.PageForm;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/21 10:35,
 * @DESC
 */
public interface SysLogService extends BaseService<SysLog> {

    List<SysLog> findByIdIn(List<Long> ids);

    PageResult findSysLoginPage(PageForm pageForm);

    boolean deleteLoginLog(List<SysLoginLog> sysLoginLogList);
    SysLoginLog saveLoginLog(String user, String ip, String status);

    List<SysLoginLog> findByLoginLogIdIn(List<Long> ids);
}

