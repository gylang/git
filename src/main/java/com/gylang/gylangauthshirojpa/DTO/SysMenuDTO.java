package com.gylang.gylangauthshirojpa.DTO;

import com.gylang.gylangauthshirojpa.domian.SysMenu;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/17 20:13,
 * @DESC
 */
@Data
public class SysMenuDTO {

    private Long id;

    private String name;

    private Long parentId;

    private String url;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private String createBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Byte delFlag;
    // 非数据库字段
    private String parentName;
    // 非数据库字段
    private Integer level;
    // 非数据库字段
    private List<SysMenu> children;
}
