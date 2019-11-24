package com.gylang.gylangauthshirojpa.service;

import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.target.OperatorArgs;

import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/16 15:47,
 * @DESC
 */
public interface BaseService<T> {

    T findById(Long id);


    T save(T t);

    PageResult findPage(PageForm pageForm);

    boolean delete(T t);

    boolean delete(List<T> t);

}
