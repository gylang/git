package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.domian.SysMenu;
import com.gylang.gylangauthshirojpa.enums.ResultEnum;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysMenuService;
import com.gylang.gylangauthshirojpa.utils.CopyUtils;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import com.gylang.gylangauthshirojpa.utils.LoginUtils;
import net.sf.saxon.expr.instruct.Copy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gylang,
 * @data 2019/11/19 16:36,
 * @DESC
 */
@RestController
@RequestMapping("menu")
public class SysMenuController extends BaseController<SysMenu> {

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public Result save(@RequestBody SysMenu sysMenu) {

        return Result.auto(null != sysMenuService.save(sysMenu));
    }

    @Override
    public Result update(@RequestBody SysMenu sysMenu) {

        SysMenu menu = sysMenuService.findById(sysMenu.getId());
        if (null == menu) {
            return Result.failure(-1, "菜单不存在");
        }
        List<String> ignore = new ArrayList<>(Arrays.asList("createTime", "createTime"));
        CopyUtils.notNullAndOtherCopy(sysMenu, menu, ignore);
        return Result.auto(null != sysMenuService.save(menu));
    }

    @Override
    public Result delete(@RequestBody List<SysMenu> t) {

        List<Long> sysMenuIdList = t.stream()
                .filter(sysConfig -> null != sysConfig.getId())
                .map(SysMenu::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(sysMenuIdList)) {
            return Result.failure(ResultEnum.ID_NULL);
        }
        List<SysMenu> menus = sysMenuService.findByIdIn(sysMenuIdList);

        // todo 按需求 判断删除条件
        String createBy = "admin";
        List<SysMenu> delete = menus.stream()
                .filter(sysMenu -> createBy.equals(sysMenu.getCreateBy()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(delete)) {
            return Result.failure(ResultEnum.RESULT_EMPTY);
        }
        return Result.auto(sysMenuService.delete(delete));

    }

    @Override
    public Result findPage(@RequestBody @Valid PageForm pageForm) {

        return Result.success(sysMenuService.findPage(pageForm));
    }

    @GetMapping("/findNavTree")
    public Result findNavTree() {

        return Result.success(sysMenuService.findTree(LoginUtils.getUserId(), 1));

    }

    @GetMapping("/findMenuTree")
    public Result findMenuTree() {

        return Result.success(sysMenuService.findTree(LoginUtils.getUserId(), 1));

    }
}
