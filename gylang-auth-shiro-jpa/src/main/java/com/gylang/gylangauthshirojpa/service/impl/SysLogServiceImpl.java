package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysLog;
import com.gylang.gylangauthshirojpa.domian.SysLoginLog;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.repository.SysLogRepository;
import com.gylang.gylangauthshirojpa.repository.SysLoginLogRepository;
import com.gylang.gylangauthshirojpa.service.SysLogService;
import com.gylang.gylangauthshirojpa.utils.dySql.Criteria;
import com.gylang.gylangauthshirojpa.utils.dySql.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/21 10:47,
 * @DESC
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogRepository sysLogRepository;
    @Autowired
    private SysLoginLogRepository sysLoginLogRepository;

    @Override
    public SysLog findById(Long id) {
        if (null == id) {
            return null;
        }
        return sysLogRepository.findById(id).orElse(null);
    }

    @Override
    public List<SysLog> findByIdIn(List<Long> ids) {
        return sysLogRepository.findByIdIn(ids);
    }

    @Override
    public List<SysLoginLog> findByLoginLogIdIn(List<Long> ids) {
        return sysLoginLogRepository.findByIdIn(ids);
    }

    @Override
    public PageResult findSysLoginPage(PageForm pageForm) {


        Criteria<SysLoginLog> criteria = new Criteria();
        criteria.add(Restrictions.eq("userName", pageForm.getParamValue("userName"), true));
        Page<SysLoginLog> sysLoginLogPage = sysLoginLogRepository.findAll(criteria, pageForm.getPage());
        return new PageResult<SysLoginLog>().setAndGet(sysLoginLogPage);
    }

    @Override
    public boolean deleteLoginLog(List<SysLoginLog> sysLoginLogList) {

        sysLoginLogRepository.deleteInBatch(sysLoginLogList);
        return true;
    }

    @Override
    public SysLog save(SysLog sysLog) {
        return sysLogRepository.save(sysLog);
    }

    @Override
    public PageResult findPage(PageForm pageForm) {

        Criteria<SysLog> criteria = new Criteria();
        criteria.add(Restrictions.like("userName", pageForm.getParamValue("userName"), true));
        Page<SysLog> sysLogPage = sysLogRepository.findAll(criteria, pageForm.getPage());
        return new PageResult<SysLog>().setAndGet(sysLogPage);
    }

    @Override
    public boolean delete(SysLog sysLog) {

        sysLogRepository.delete(sysLog);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(List<SysLog> t) {

        sysLogRepository.deleteInBatch(t);
        return true;
    }
}
