package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.VO.Result;
import com.gylang.gylangauthshirojpa.domian.SysLog;
import com.gylang.gylangauthshirojpa.enums.ResultEnum;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
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
public class SysLogController  {

    @Autowired
    private SysLogService sysLogService;


    @PostMapping("/delete")
    public Result delete(@RequestBody List<SysLog> t) {

        List<Long> logIds = t.stream()
                .filter(sysConfig -> null != sysConfig.getId())
                .map(SysLog::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(logIds)) {
            return Result.failure(ResultEnum.ID_NULL);
        }
        List<SysLog> sysLogList = sysLogService.findByIdIn(logIds);
        if (CollectionUtils.isEmpty(sysLogList)) {
            return Result.failure(ResultEnum.RESULT_EMPTY);
        }

        return Result.auto(sysLogService.delete(sysLogList));
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody @Valid PageForm  pageForm) {

        return Result.success(sysLogService.findPage(pageForm));
    }
}
