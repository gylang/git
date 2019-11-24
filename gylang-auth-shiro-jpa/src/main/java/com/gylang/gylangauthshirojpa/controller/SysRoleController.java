package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.contant.SysConstants;
import com.gylang.gylangauthshirojpa.domian.SysRole;
import com.gylang.gylangauthshirojpa.enums.ResultEnum;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysRoleService;
import com.gylang.gylangauthshirojpa.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gylang,
 * @data 2019/11/19 21:17,
 * @DESC
 */
@RestController
@RequestMapping("role")
public class SysRoleController extends BaseController<SysRole> {

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public Result save(@RequestBody SysRole sysRole) {

        return Result.auto(null != sysRoleService.save(sysRole));
    }

    @Override
    public Result update(@RequestBody SysRole sysRole) {

        SysRole role = sysRoleService.findById(sysRole.getId());
        if (null == role) {
            return Result.failure(-1, "角色不存在");
        }
        List<String> ignore = Arrays.asList("createBy", "createTime");
        CopyUtils.notNullAndOtherCopy(sysRole, role, ignore);
        return Result.auto(null != sysRoleService.save(role));
    }

    @Override
    public Result delete(@RequestBody List<SysRole> t) {

        System.out.println(t);
        List<Long> roleIdList = t.stream()
                .filter(sysConfig -> null != sysConfig.getId())
                .map(SysRole::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Result.failure(ResultEnum.ID_NULL);
        }
        List<SysRole> roleList = sysRoleService.findByIdIn(roleIdList);

        List<SysRole> delete = roleList.stream()
                .filter(sysRole -> !SysConstants.ADMIN.equals(sysRole.getName()))
                .collect(Collectors.toList());

        return Result.auto(sysRoleService.delete(delete));
    }

    @Override
    public Result findPage(@RequestBody @Valid PageForm pageForm) {

        return Result.success(sysRoleService.findPage(pageForm));
    }
}
