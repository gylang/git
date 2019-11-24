package com.gylang.gylangauthshirojpa.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author arctique
 * @date 2019-07-19 12:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    /** 总数目     */
    private Long total;

    private Integer totalPage;

    /** 页面条数    */
    private Integer size;

    /** 页码       */
    private Integer page;

    /** 内容       */
    private List<T> content;


    public PageResult setAndGet(Page<T> page) {

        this.page = page.getNumber() + 1;
        this.content = page.getContent();
        this.size = page.getSize();
        this.totalPage = page.getTotalPages();
        this.total = page.getTotalElements();
        return this;

    }



}
