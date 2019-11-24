package com.gylang.gylangauthshirojpa.domian;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class SysMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Transient
    private String parentName;
    // 非数据库字段
    @Transient
    private Integer level;
    // 非数据库字段
    @Transient
    private List<SysMenu> children;


}
