package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.domian.SysDict;
import com.gylang.gylangauthshirojpa.enums.ResultEnum;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysDictService;
import com.gylang.gylangauthshirojpa.utils.CopyUtils;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
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
 * @data 2019/11/21 12:17,
 * @DESC
 */
@RestController
@RequestMapping("dict")
public class SysDictController extends BaseController<SysDict> {

    @Autowired
    private SysDictService sysDictService;

    @Override
    public Result save(@RequestBody @Valid SysDict sysDict) {
        System.out.println(JsonUtils.obj2Json(sysDict));

        return Result.auto(null != sysDictService.save(sysDict));
    }

    @Override
    public Result update(@RequestBody SysDict sysDict) {

        if (null == sysDict.getId()) {
            return Result.failure(ResultEnum.ID_NULL);
        }
        System.out.println(JsonUtils.obj2Json(sysDict));
        SysDict dict = sysDictService.findById(sysDict.getId());
        if (dict == null) {
            return Result.failure(ResultEnum.RESULT_EMPTY);
        }
        List<String> ignore = Arrays.asList("createBy");
        CopyUtils.notNullAndOtherCopy(sysDict, dict, ignore);

        return Result.auto(null != sysDictService.save(dict));
    }

    @Override
    public Result delete(@RequestBody List<SysDict> t) {

        List<Long> ids = t.stream()
                .filter(sysConfig -> null != sysConfig.getId())
                .map(SysDict::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(ids)) {
            return Result.failure(ResultEnum.ID_NULL);
        }
        List<SysDict> dicts = sysDictService.findByIdIn(ids);
        LoginInfoDTO loginInfoDTO = LoginUtils.getLoginInfo();
        List<SysDict> delete = dicts.stream()
                .filter(sysDict -> loginInfoDTO.getName().equals(sysDict.getCreateBy()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(delete)) {
            return Result.failure(ResultEnum.RESULT_EMPTY);
        }
        return Result.auto(sysDictService.delete(delete));
    }


    @Override
    public Result findPage(@RequestBody @Valid PageForm pageForm) {

        return Result.success(sysDictService.findPage(pageForm));
    }
}
