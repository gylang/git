package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysMenu;
import com.gylang.gylangauthshirojpa.domian.SysRoleMenu;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.repository.SysRoleMenuRepository;
import com.gylang.gylangauthshirojpa.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/17 17:05,
 * @DESC
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Override
    public SysRoleMenu findById(Long id) {

        if (null == id) {
            return null;
        } else {
            return sysRoleMenuRepository.findById(id).orElse(null);
        }

    }

    @Override
    public SysRoleMenu save(SysRoleMenu sysRoleMenu) {


        return sysRoleMenuRepository.save(sysRoleMenu);
    }

    @Override
    public PageResult findPage(PageForm pageForm) {

        Page<SysRoleMenu> sysRoleMenuPage =  sysRoleMenuRepository.findAll(pageForm.getPage());
        return new PageResult<SysRoleMenu>().setAndGet(sysRoleMenuPage);
    }

    @Override
    public boolean delete(SysRoleMenu sysRoleMenu) {
        sysRoleMenuRepository.delete(sysRoleMenu);
        return true;
    }


    @Override
    public boolean delete(List<SysRoleMenu> t) {
        return false;
    }

    @Override
    public List<SysRoleMenu> findByRoleId(Long roleId) {
        return sysRoleMenuRepository.findByRoleId(roleId);
    }

    @Override
    public List<SysRoleMenu> findByRoleId(Collection<Long> roleId) {
        return sysRoleMenuRepository.findByRoleIdIn(roleId);
    }

    @Override
    public void deleteByMenuIdIn(List<Long> menuId) {
        sysRoleMenuRepository.deleteByMenuIdIn(menuId);
    }

    @Override
    public void deleteByRoleIdIn(List<Long> roleId) {

        sysRoleMenuRepository.deleteByRoleIdIn(roleId);
    }
}
