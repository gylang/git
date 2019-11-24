package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysUserRole;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.repository.SysUserRoleRepository;
import com.gylang.gylangauthshirojpa.service.SysUserRoleService;
import com.gylang.gylangauthshirojpa.utils.dySql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/17 17:01,
 * @DESC
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Override
    public  List<SysUserRole> findByUserId(Long userId) {

        return sysUserRoleRepository.findByUserId(userId);
    }

    @Override
    public SysUserRole findById(Long id) {
        return sysUserRoleRepository.findById(id).orElse(null);

    }

    @Override
    public SysUserRole save(SysUserRole sysUserRole) {

        return sysUserRoleRepository.save(sysUserRole);
    }

    @Override
    public PageResult findPage(PageForm pageForm) {

        Page<SysUserRole> sysUserRolePage = sysUserRoleRepository.findAll(pageForm.getPage());
        return new PageResult<SysUserRole>().setAndGet(sysUserRolePage);
    }

    @Override
    public boolean delete(SysUserRole sysUserRole) {

        sysUserRoleRepository.delete(sysUserRole);
        return true;
    }


    @Override
    @Transactional
    public boolean delete(List<SysUserRole> t) {

        sysUserRoleRepository.deleteInBatch(t);
        return true;
    }
}
