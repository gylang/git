package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.domian.SysMenu;
import com.gylang.gylangauthshirojpa.domian.SysUserRole;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysUser;
import com.gylang.gylangauthshirojpa.repository.SysUserRepository;
import com.gylang.gylangauthshirojpa.service.SysMenuService;
import com.gylang.gylangauthshirojpa.service.SysUserRoleService;
import com.gylang.gylangauthshirojpa.service.SysUserService;
import com.gylang.gylangauthshirojpa.target.OperatorArgs;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import com.gylang.gylangauthshirojpa.utils.dySql.Criteria;
import com.gylang.gylangauthshirojpa.utils.dySql.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gylang,
 * @data 2019/11/16 9:37,
 * @DESC
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public SysUser findByUserName(String userName) {

        return sysUserRepository.findByName(userName);
    }

    @Override
    public List<SysUser> findByIdIn(List<Long> sysUserIdList) {

        List<SysUser> sysUserList = sysUserRepository.findByIdIn(sysUserIdList);
        return sysUserList;
    }

    @Override
    public SysUser findById(Long id) {
        if (null == id) {
            return null;
        }
        return sysUserRepository.findById(id).orElse(null);
    }

    @Transactional
    public SysUser save(SysUser sysUser) {

        return  sysUserRepository.save(sysUser);
    }

    @Override

    public PageResult findPage(PageForm pageForm) {

        Criteria<SysUser> sysUserCriteria = new Criteria<>();
        sysUserCriteria.add(Restrictions.like("nickName", pageForm.getParamValue("nickName"), true));
        Page<SysUser> sysUserPage = sysUserRepository.findAll(sysUserCriteria, pageForm.getPage());
        return new PageResult<SysUser>().setAndGet(sysUserPage);
    }

    @Override
    @Transactional
    public boolean delete(SysUser sysUser) {

        sysUserRepository.delete(sysUser);
        return true;
    }

    @Override
    public Set<String> findPermit(Long id) {

        List<SysMenu> sysMenuList = sysMenuService.findByUserId(id);
        Set<String> permitList = sysMenuList.stream()
                .filter(sysMenu -> !StringUtils.isEmpty(sysMenu.getPerms()))
                .map(SysMenu::getPerms).collect(Collectors.toSet());
        return permitList;
    }

    @Override
    public Set<String> findAllPermit() {
        List<SysMenu> sysMenuList = sysMenuService.findAll();
        Set<String> permitList = sysMenuList.stream()
                .filter(sysMenu -> !StringUtils.isEmpty(sysMenu.getPerms()))
                .map(SysMenu::getPerms).collect(Collectors.toSet());
        return permitList;
    }

    @Override
    public List<SysUserRole> findUserRoles(Long userId) {
        return sysUserRoleService.findByUserId(userId);
    }

    @Override
    public boolean delete(List<SysUser> t) {
        sysUserRepository.deleteInBatch(t);
        return true;
    }
}
