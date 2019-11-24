package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.domian.SysConfig;
import com.gylang.gylangauthshirojpa.enums.ResultEnum;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysConfigService;
import com.gylang.gylangauthshirojpa.utils.CopyUtils;
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
 * @data 2019/11/21 12:56,
 * @DESC
 */
@RestController
@RequestMapping("config")
public class SysConfigController extends BaseController<SysConfig> {

    @Autowired
    private SysConfigService sysConfigService;


    @Override
    public Result save(@RequestBody @Valid SysConfig sysConfig) {

        return Result.auto(null != sysConfigService.save(sysConfig));
    }

    @Override
    public Result update(@RequestBody SysConfig sysConfig) {

        SysConfig config = sysConfigService.findById(sysConfig.getId());
        if (null == config) {
            return Result.failure(-1, "配置信息");
        }
        List<String> ignore = Arrays.asList("createBy", "createTime");
        CopyUtils.notNullAndOtherCopy(sysConfig, config, ignore);
        return Result.auto(null != sysConfigService.save(config));

    }

    @Override
    public Result delete(@RequestBody List<SysConfig> t) {

        List<Long> ids = t.stream()
                .filter(sysConfig -> null != sysConfig.getId())
                .map(SysConfig::getId).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(ids)) {
            return Result.failure(ResultEnum.ID_NULL);
        }
        List<SysConfig> config = sysConfigService.findByIdIn(ids);

        // todo 按需求 判断删除条件
        String createBy = "admin";
        List<SysConfig> delete = config.stream()
                .filter(sysConfig -> createBy.equals(sysConfig.getCreateBy()))
                .collect(Collectors.toList());

        return Result.auto(sysConfigService.delete(delete));

    }

    @Override
    public Result findPage(@RequestBody @Valid PageForm pageForm) {
        return Result.success(sysConfigService.findPage(pageForm));
    }
}
