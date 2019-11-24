package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.DTO.SysMenuDTO;
import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysMenu;
import com.gylang.gylangauthshirojpa.domian.SysRoleMenu;
import com.gylang.gylangauthshirojpa.domian.SysUser;
import com.gylang.gylangauthshirojpa.domian.SysUserRole;
import com.gylang.gylangauthshirojpa.enums.AuthEnum;
import com.gylang.gylangauthshirojpa.enums.UserEnum;
import com.gylang.gylangauthshirojpa.exception.domian.AuthException;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.repository.SysMenuRepository;
import com.gylang.gylangauthshirojpa.service.*;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import com.gylang.gylangauthshirojpa.utils.dySql.Criteria;
import com.gylang.gylangauthshirojpa.utils.dySql.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gylang,
 * @data 2019/11/17 16:25,
 * @DESC
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    @Override
    public List<SysMenu> findByUserId(Long userId) {

        /**
         * todo 用户角色查询 用户关联的角色Id
         * todo 角色权限表查询 根据角色Id 查询相应的权限Id
         * todo 权限表查询 根据权限Id 查询相关的权限
         */
        if (null == userId) {
            throw new AuthException(UserEnum.NO_USER);
        }
        List<SysUserRole> sysUserRole = sysUserRoleService.findByUserId(userId);

        if (CollectionUtils.isEmpty(sysUserRole)) {
            //当前用户不存在角色
            throw new AuthException(AuthEnum.NO_ROLE);
        }
        List<Long> roleList = sysUserRole.stream()
                .map(SysUserRole::getRoleId).collect(Collectors.toList());
        // 超管拥有所有权限
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.findByRoleId(roleList);

        List<SysMenu> sysMenuList = new ArrayList<>();
        // 判断是否拥有权限 没有就 返回空权限
        if (CollectionUtils.isEmpty(sysRoleMenuList)) {
            return sysMenuList;
        }
        //获取权限IdList
        List<Long> sysMenuIdList = sysRoleMenuList.stream()
                .map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        return sysMenuRepository.findByIdIn(sysMenuIdList);

    }

    @Override
    public List<SysMenu> findTree(Long userId, int menuType) {
        List<SysMenu> enums;
        if (null == userId) {

            enums = findByUserId(userId);
        } else {
            enums = sysMenuRepository.findAll();
        }
        List<SysMenu> sysMenuLists = new ArrayList<>();
        for (SysMenu sysMenu : enums) {
            if (null == sysMenu.getParentId() || 0 == sysMenu.getParentId()) {

                sysMenu.setLevel(0);
                if (!exists(sysMenuLists, sysMenu)) {
                    sysMenuLists.add(sysMenu);
                }

            }
        }
        sysMenuLists.sort(Comparator.comparing(SysMenu::getOrderNum));
        findChildren(sysMenuLists, enums, menuType);
        return sysMenuLists;
    }

    @Override
    public List<SysMenu> findByIdIn(List<Long> idList) {

        return sysMenuRepository.findByIdIn(idList);
    }

    private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
        boolean exist = false;
        for (SysMenu menu : sysMenus) {
            if (menu.getId().equals(sysMenu.getId())) {
                exist = true;
            }
        }
        return exist;
    }

    private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
        for (SysMenu SysMenu : SysMenus) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                if (menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue;
                }
                if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
                    menu.setParentName(SysMenu.getName());
                    menu.setLevel(SysMenu.getLevel() + 1);
                    if (!exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }
            SysMenu.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
            findChildren(children, menus, menuType);
        }
    }

    @Override
    public SysMenu findById(Long id) {
        if (null == id) {
            return null;
        }
        return sysMenuRepository.findById(id).orElse(null);
    }

    @Override
    public SysMenu save(SysMenu sysMenu) {
        System.out.println(JsonUtils.obj2Json(sysMenu));
        return sysMenuRepository.save(sysMenu);
    }

    @Override
    public PageResult findPage(PageForm pageForm) {

        Criteria<SysMenu> criteria = new Criteria<>();
        criteria.add(Restrictions.like("name", pageForm.getParamValue("name"), true));
        Page<SysMenu> sysMenuPage = sysMenuRepository.findAll(criteria, pageForm.getPage());
        return new PageResult<SysMenu>().setAndGet(sysMenuPage);
    }

    @Override
    public boolean delete(SysMenu sysMenu) {

        sysMenuRepository.delete(sysMenu);
        return true;
    }

    @Override
    public List<SysMenu> findAll() {
        return sysMenuRepository.findAll();
    }

    @Override
    public boolean delete(List<SysMenu> t) {

        sysMenuRepository.deleteInBatch(t);
        return true;
    }
}
