package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysRole;
import com.gylang.gylangauthshirojpa.domian.SysUserRole;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.repository.SysRoleRepository;
import com.gylang.gylangauthshirojpa.service.SysRoleService;
import com.gylang.gylangauthshirojpa.service.SysUserRoleService;
import com.gylang.gylangauthshirojpa.utils.dySql.Criteria;
import com.gylang.gylangauthshirojpa.utils.dySql.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gylang,
 * @data 2019/11/17 16:35,
 * @DESC
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public SysRole findById(Long id) {
        if (null == id) {
            return null;
        } else {
        return sysRoleRepository.findById(id).orElse(null);
        }
    }

    @Override
    public SysRole save(SysRole sysRole) {
        return sysRoleRepository.save(sysRole);
    }

    @Override
    public PageResult findPage(PageForm pageForm) {

        Criteria<SysRole> criteria = new Criteria<SysRole>();
        criteria.add(Restrictions.like("name", pageForm.getParamValue("name"), false));

        Page<SysRole> sysRolePage =  sysRoleRepository.findAll(criteria, pageForm.getPage());
        return new PageResult<SysRole>().setAndGet(sysRolePage);
    }

    @Override
    public boolean delete(SysRole sysRole) {

        sysRoleRepository.delete(sysRole);
        return true;
    }


    @Override
    public boolean delete(List<SysRole> t) {

        sysRoleRepository.deleteInBatch(t);
        return true;
    }

    @Override
    public List<SysRole> findByUserId(Long userId) {

        if (null == userId) {
            return new ArrayList<>();
        }
        List<SysUserRole> sysUserRoleList = sysUserRoleService.findByUserId(userId);

        List<Long> roleIdList = sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return sysRoleRepository.findByIdIn(roleIdList);
    }

    @Override
    public List<SysRole> findByIdIn(List<Long> idList) {

        return sysRoleRepository.findByIdIn(idList);
    }
}
