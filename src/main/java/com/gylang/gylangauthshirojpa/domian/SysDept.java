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
public class SysDept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long parentId;

    private Integer orderNum;

    private String createBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Byte delFlag;

    // 非数据库字段
    @Transient
    private List<SysDept> children;

    // 非数据库字段
    @Transient
    private String parentName;

    // 非数据库字段
    @Transient
    private Integer level;
}
