package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.domian.SysLog;
import com.gylang.gylangauthshirojpa.domian.SysLoginLog;
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
 * @data 2019/11/21 22:06,
 * @DESC
 */
@RestController
@RequestMapping("loginLog")
public class SysLoginLogController extends BaseController<SysLoginLog> {

    @Autowired
    private SysLogService sysLogService;

    @Override
    public Result save(SysLoginLog sysLoginLog) {
        return null;
    }

    @Override
    public Result update(SysLoginLog sysLoginLog) {
        return null;
    }

    @Override
    public Result delete(@RequestBody List<SysLoginLog> t) {

        List<Long> logIds = t.stream()
                .filter(sysConfig -> null != sysConfig.getId())
                .map(SysLoginLog::getId).collect(Collectors.toList());;
        List<SysLoginLog> sysLogList = sysLogService.findByLoginLogIdIn(logIds);

        return Result.auto(sysLogService.deleteLoginLog(sysLogList));
    }

    @Override
    public Result findPage(@RequestBody @Valid PageForm pageForm) {
        return Result.success(sysLogService.findPage(pageForm));
    }
}
