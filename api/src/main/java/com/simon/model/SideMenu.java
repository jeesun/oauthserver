package com.simon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simon.common.config.AppConfig;
import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* 侧边菜单
* @author SimonSun
* @date 2018-10-02
**/
@ApiModel(value = "侧边菜单")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_side_menu")
public class SideMenu implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "菜单名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "菜单地址")
    @Column(name = "url")
    private String url;

    @ApiModelProperty(value = "菜单权限")
    @Column(name = "authority")
    private String authority;

    @ApiModelProperty(value = "菜单图标")
    @Column(name = "icon")
    private String icon;

    @ApiModelProperty(value = "菜单组id")
    @Column(name = "pid")
    private Long pid;

    @ApiModelProperty(value = "排序")
    @Column(name = "order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单类型[1:一级菜单, 2:二级菜单]")
    @Transient
    private Integer menuType;

    @ApiModelProperty(value = "子菜单列表")
    @Transient
    private List<SideMenu> subSideMenus;
}