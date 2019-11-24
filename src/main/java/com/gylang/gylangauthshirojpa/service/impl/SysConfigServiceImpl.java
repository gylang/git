package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysConfig;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.repository.SysConfigRepository;
import com.gylang.gylangauthshirojpa.service.SysConfigService;
import com.gylang.gylangauthshirojpa.target.OperatorArgs;
import com.gylang.gylangauthshirojpa.utils.dySql.Criteria;
import com.gylang.gylangauthshirojpa.utils.dySql.Restrictions;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/21 13:01,
 * @DESC
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Override
    public List<SysConfig> findByIdIn(List<Long> ids) {

        return sysConfigRepository.findByIdIn(ids);
    }

    @Override
    public SysConfig findById(Long id) {

        if (null == id) {
            return null;
        } else {
            return sysConfigRepository.findById(id).orElse(null);
        }
    }

    @Override
    public SysConfig save(SysConfig sysConfig) {
        return sysConfigRepository.save(sysConfig);
    }

    @Override
    public PageResult findPage( PageForm pageForm) {

        Criteria<SysConfig> criteria = new Criteria<>();
        criteria.add(Restrictions.like("label", pageForm.getParamValue("label"), true));
        criteria.add(Restrictions.like("type", pageForm.getParamValue("type"), true));
        Page<SysConfig> sysConfigPage = sysConfigRepository.findAll(criteria, pageForm.getPage());

        //过滤数据
        Iterator<SysConfig> iterator = sysConfigPage.iterator();
        while (iterator.hasNext()) {
            SysConfig sysConfig = iterator.next();
            sysConfig.setCreateBy(null);
            sysConfig.setDelFlag(null);
            sysConfig.setLastUpdateBy(null);
        }
        return new PageResult<SysConfig>().setAndGet(sysConfigPage);
    }

    @Override
    public boolean delete(SysConfig sysConfig) {

        sysConfigRepository.delete(sysConfig);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(List<SysConfig> t) {

        sysConfigRepository.deleteInBatch(t);
        return true;
    }
}
