package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.domian.SysLog;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gylang,
 * @data 2019/11/21 10:27,
 * @DESC
 */
@RequestMapping("log")
@RestController
public class SysLogController extends BaseController<SysLog> {

    @Autowired
    private SysLogService sysLogService;
    @Override
    public Result save(@RequestBody SysLog sysLog) {
        return null;
    }

    @Override
    public Result update(@RequestBody SysLog sysLog) {
        return null;
    }

    @Override
    public Result delete(@RequestBody List<SysLog> t) {

        List<Long> logIds = t.stream()
                .filter(sysConfig -> null != sysConfig.getId())
                .map(SysLog::getId).collect(Collectors.toList());;
        List<SysLog> sysLogList = sysLogService.findByIdIn(logIds);


        return Result.auto(sysLogService.delete(sysLogList));
    }

    @Override
    public Result findPage(@RequestBody @Valid PageForm  pageForm) {
        return Result.success(sysLogService.findPage(pageForm));
    }
}
