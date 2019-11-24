package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.domian.SysDept;
import com.gylang.gylangauthshirojpa.enums.ResultEnum;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysDeptService;
import com.gylang.gylangauthshirojpa.utils.CopyUtils;
import com.gylang.gylangauthshirojpa.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gylang,
 * @data 2019/11/21 9:47,
 * @DESC
 */
@RestController
@RequestMapping("dept")
public class SysDeptController extends BaseController<SysDept> {

    @Autowired
    private SysDeptService sysDeptService;

    @Override
    public Result save(@RequestBody SysDept sysDept) {


        return Result.auto(null != sysDeptService.save(sysDept));
    }

    @Override
    public Result update(@RequestBody SysDept sysDept) {

        SysDept dept = new SysDept();

        List<String> ignore = Arrays.asList("createTime", "createBy");
        CopyUtils.notNullAndOtherCopy(sysDept, dept, ignore);

        return Result.auto(null != sysDeptService.save(dept));
    }

    @Override
    public Result delete(@RequestBody List<SysDept> t) {

        List<Long> deptId = t.stream()
                .filter(sysConfig -> null != sysConfig.getId())
                .map(SysDept::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(deptId)) {
            return Result.failure(ResultEnum.ID_NULL);
        }
        List<SysDept> sysDeptList = sysDeptService.findByIdIn(deptId);

        LoginInfoDTO loginInfoDTO = LoginUtils.getLoginInfo();
        List<SysDept> delete = sysDeptList.stream()
                .filter(sysDept -> loginInfoDTO.getName().equals(sysDept.getCreateBy()))
                .collect(Collectors.toList());

        return Result.auto(sysDeptService.delete(delete));
    }

    @Override
    public Result findPage(@RequestBody @Valid PageForm pageForm) {

        return Result.success(sysDeptService.findPage(pageForm));
    }
}
