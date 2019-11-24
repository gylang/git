package com.gylang.gylangauthshirojpa.controller;

import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.VO.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/16 16:21,
 * @DESC
 */

public abstract class BaseController<T> {

    @PostMapping("/save")
    public abstract Result save(T t);

    @PostMapping("/update")
    public abstract Result update(T t);

    @PostMapping("/delete")
    public abstract Result delete(List<T> t);

    @PostMapping("/findPage")
    public abstract Result findPage(PageForm pageForm);
}
