package com.simon.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.simon.common.config.AppConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* Font Awesome字体图标
* @author SimonSun
* @date 2019-04-29
**/
@ApiModel(description = "Font Awesome字体图标")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_s_font_awesome")
public class FontAwesome implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "图标class")
    @Column(name = "icon_class", nullable = false)
    private String iconClass;

    @ApiModelProperty(value = "英文标签")
    @Column(name = "label", nullable = false)
    private String label;

    @ApiModelProperty(value = "中文标签")
    @Column(name = "tags")
    private String tags;

    @ApiModelProperty(value = "排序")
    @Column(name = "order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "状态")
    @Column(name = "status")
    private Integer status;

    @ApiModelProperty(value = "创建人名称")
    @Transient
    private String createUser;

    @ApiModelProperty(value = "更新人名称")
    @Transient
    private String updateUser;
}