package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.contant.SysConstants;
import com.gylang.gylangauthshirojpa.domian.SysMenu;
import com.gylang.gylangauthshirojpa.enums.ResultEnum;
import com.gylang.gylangauthshirojpa.enums.UserEnum;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.domian.SysUser;
import com.gylang.gylangauthshirojpa.service.SysMenuService;
import com.gylang.gylangauthshirojpa.service.SysUserService;
import com.gylang.gylangauthshirojpa.target.OperatorArgs;
import com.gylang.gylangauthshirojpa.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gylang,
 * @data 2019/11/16 16:14,
 * @DESC
 */
@RestController
@RequestMapping("user")
@Slf4j
public class SysUserController extends BaseController<SysUser> {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;


    public Result findPage(@RequestBody @Valid PageForm pageForm) {

        PageResult pageResult = sysUserService.findPage(pageForm);
        return Result.success(pageResult);
    }


    public Result save(@RequestBody @Valid SysUser sysUser) {

//        SysUser user = sysUserService.findById(sysUser.getId());
//        if (null == user) {
        // todo 检验当前用户是否存在 和格式是否符合要求
        if (!ValueValidUtils.validUserName(sysUser.getName())) {
            return Result.failure(ResultEnum.PARAMS_ERROR.getCode(), "账号格式不正确");
        }
        SysUser user = sysUserService.findByUserName(sysUser.getName());
        if (!ObjectUtils.isEmpty(user)) {
            return Result.failure(ResultEnum.USER_HAVE_REGISTER);
//            }
        }

        // todo 可修改信息
        user = new SysUser();
        if (!StringUtils.isEmpty(sysUser.getPassword())) {
            String salt = Md5Util.generSalt();
            String password = Md5Util.passWdMd5(sysUser.getPassword(), salt);
            user.setPassword(password);
            user.setSalt(salt);
        }
//        todo 第一种 直接set方法和判断是否为空
//        user.setNickName(null == sysUser.getNickName() ? user.getNickName() : sysUser.getNickName());
//        user.setStatus(null == sysUser.getStatus() ? user.getStatus() : sysUser.getStatus());
//        user.setAvatar(null == sysUser.getAvatar() ? user.getAvatar() : sysUser.getAvatar());
//        user.setDelFlag(null == sysUser.getDelFlag() ? user.getDelFlag() : sysUser.getDelFlag());
//        user.setName(null == sysUser.getName() ? user.getName() : sysUser.getName());
//        user.setEmail(null == sysUser.getEmail() ? user.getEmail() : sysUser.getEmail());
//        user.setMobile(null == sysUser.getMobile() ? user.getMobile() : sysUser.getMobile());
//
//   todo 第二种 封装 拷贝方法 忽略不需要拷贝的属性
        List<String> ignore = new ArrayList<>();
        ignore.addAll(Arrays.asList("status"));
        CopyUtils.notNullAndOtherCopy(sysUser, user, ignore);

        return Result.auto(null != sysUserService.save(user));
    }

    @Override
    @OperatorArgs
    public Result update(@RequestBody SysUser sysUser) {

        //todo 查询待修改用户 检验是否符合需求
        SysUser user = sysUserService.findByUserName(sysUser.getName());

        if (null == user) {
            return Result.failure(UserEnum.NO_USER);
        }

        // todo 设置修改需要修改的属性
        List<String> ignore = new ArrayList<>();
        ignore.addAll(Arrays.asList("id", "password", "salt"));

        CopyUtils.notNullAndOtherCopy(sysUser, user, ignore);
        //修改密码
        if (!StringUtils.isEmpty(sysUser.getPassword())) {
            String salt = Md5Util.generSalt();
            String passWd = Md5Util.passWdMd5(sysUser.getPassword(), salt);
            user.setPassword(passWd);
            user.setSalt(salt);
        }

        return Result.auto(null != sysUserService.save(user));
    }

    @Override
    public Result delete(@RequestBody List<SysUser> t) {
        // 查询需要删除的用户
        List<Long> ids = t.stream()
                .filter(sysConfig -> null != sysConfig.getId())
                .map(SysUser::getId).collect(Collectors.toList());
        List<SysUser> sysUserList = sysUserService.findByIdIn(ids);
        if (CollectionUtils.isEmpty(ids)) {
            return Result.failure(ResultEnum.ID_NULL);
        }

        // todo 按删除需求条件 添加符合的到待删除 List
        List<SysUser> deleteUser = sysUserList.stream()
                .filter(sysUser -> !SysConstants.ADMIN.equals(sysUser.getName()))
                .filter(sysUser -> SysConstants.ADMIN.equals(sysUser.getCreateBy()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(deleteUser)) {
            return Result.failure(ResultEnum.RESULT_EMPTY);
        }
        System.out.println(JsonUtils.obj2Json(deleteUser));
        return Result.success(sysUserService.delete(deleteUser));
    }

    @GetMapping("/findPermit")
    public Result findPermit() {

        LoginInfoDTO loginInfoDTO = LoginUtils.getLoginInfo();
        if (SysConstants.ADMIN.equals(loginInfoDTO.getName())) {
            return Result.success(sysMenuService.findAll());
        } else {
            return Result.success(sysUserService.findPermit(loginInfoDTO.getId()));
        }
    }
}
