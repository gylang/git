package com.gylang.gylangauthshirojpa.form;

import com.gylang.gylangauthshirojpa.VO.Param;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 分页请求
 *
 * @author gylang
 * @date Jan 12, 2019
 */
@Data
public class PageForm {
    /**
     * 当前页码
     */
    @Min(value = 1, message = "页码不能小于1")

    private int pageNum = 1;
    /**
     * 每页数量
     */
    @Min(value = 1,message = "页面大小不能小于1")
    private int pageSize = 10;

    /**
     * 排序的列名 order by (orderName)
     */
    private List<String> orderName = new ArrayList<String>();

    /**
     * 排序类型 0倒叙 1顺序
     */
    private Integer orderType = null;

    /**
     * 查询参数
     */
    private List<Param> params = new ArrayList<>();


    /**
     * 查询参数对象
     *
     * @param name 参数名称
     * @return
     */
    public Param getParam(String name) {

        Iterator<Param> iterator = this.params.iterator();
        while (iterator.hasNext()) {
            Param param = iterator.next();
            if (name != null && name.equals(param.getName())) {
                return param;
            }
        }
//        for (Param param : this.params) {
//            if (name != null && name.equals(param.getName())) {
//                return param;
//            }
//        }
        return null;
    }

    /**
     * 查询参数值
     * @param name 参数名称
     * @return
     */
    public String getParamValue(String name) {
        Param param = getParam(name);
        if (param != null) {
            return param.getValue();
        }
        return null;
    }

    /**
     * 获取 jpa分页对象PageRequest 设置排序 如果sort 不为null
     * @return
     */
    public PageRequest getPage() {
        Sort sort = getSort();
        if (null == sort) {
            return PageRequest.of(pageNum - 1, pageSize);
        } else {
            return PageRequest.of(pageNum - 1, pageSize, sort);
        }
    }

    /**
     * 1 为顺序 0为倒叙
     */
    public Sort getSort() {
        if (null == orderType || 1 < orderType) {
            return null;
        }
        if (0 == orderType) {

            return Sort.by(Sort.Direction.DESC, getOrderNameStrArray());
        } else {

            return Sort.by(Sort.Direction.ASC, getOrderNameStrArray());
        }

    }

    public String[] getOrderNameStrArray() {

        return orderName.toArray(new String[orderName.size()]);
    }

    /**
     * 设置分页参数
     * @param key
     * @param value
     */
    public void setParam(String key, String value) {
        this.params.add(new Param(key, value));
    }

}
