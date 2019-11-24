package com.gylang.gylangauthshirojpa.service.impl;

/**
 * @author gylang,
 * @data 2019/11/21 12:29,
 * @DESC
 */

import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysDict;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.repository.SysDictRepository;
import com.gylang.gylangauthshirojpa.service.SysDictService;
import com.gylang.gylangauthshirojpa.utils.dySql.Criteria;
import com.gylang.gylangauthshirojpa.utils.dySql.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictRepository sysDictRepository;

    @Override
    public SysDict findById(Long id) {

        if (null == id) {
            return null;
        } else {
            return sysDictRepository.findById(id).orElse(null);
        }

    }

    @Override
    public List<SysDict> findByIdIn(List<Long> ids) {
        return sysDictRepository.findByIdIn(ids);
    }

    @Override
    public SysDict save(SysDict sysDict) {

        return sysDictRepository.save(sysDict);
    }

    @Override
    public PageResult findPage(PageForm pageForm) {

        Criteria<SysDict> criteria = new Criteria<>();
        criteria.add(Restrictions.like("label", pageForm.getParamValue("label"), true));
        criteria.add(Restrictions.like("type", pageForm.getParamValue("type"), true));
        Page<SysDict> sysDictPage = sysDictRepository.findAll(criteria, pageForm.getPage());

        return new PageResult<SysDict>().setAndGet(sysDictPage);
    }

    @Override
    public boolean delete(SysDict sysDict) {

        sysDictRepository.delete(sysDict);
        return true;
    }

    @Override
    public boolean delete(List<SysDict> t) {

        sysDictRepository.deleteInBatch(t);
        return true;
    }
}
