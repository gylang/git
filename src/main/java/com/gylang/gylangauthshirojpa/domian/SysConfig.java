package com.gylang.gylangauthshirojpa.domian;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class SysConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "value不能为空")
    private String value;

    @NotBlank(message = "标签不能为空")
    private String label;

    @NotBlank(message = "类型不能为空")
    private String type;

    private String description;

    private BigDecimal sort;

    private String createBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private String remarks;

    private Byte delFlag;




}
