package com.simon.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
 *
 * @author SimonSun
 * @date 2018-10-02
 **/
@ApiModel(value = "侧边菜单")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "t_side_menu")
public class SideMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @Id
    @Column(name = "id")
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "菜单名称")
    @Transient
    private String name;

    @ApiModelProperty(value = "菜单地址")
    @Column(name = "url")
    private String url;

    @ApiModelProperty(value = "请求方法")
    @Column(name = "request_method")
    private String requestMethod;

    @ApiModelProperty(value = "菜单图标")
    @Column(name = "icon")
    private String icon;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "菜单组id")
    @Column(name = "pid")
    private Long pid;

    @ApiModelProperty(value = "排序")
    @Column(name = "order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "是否显示在菜单列表")
    @Column(name = "show_in_menu")
    private Boolean showInMenu;

    @ApiModelProperty(value = "实体类名")
    @Column(name = "entity_name")
    private String entityName;

    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "关联id")
    @Column(name = "link_id")
    private Long linkId;

    @ApiModelProperty(value = "菜单类型[1:一级菜单, 2:二级菜单, 3:三级菜单]")
    @Column(name = "menu_type")
    private Integer menuType;

    @ApiModelProperty(value = "子菜单列表")
    @Transient
    private List<SideMenu> subSideMenus;

    @ApiModelProperty(value = "菜单权限，多个用逗号隔开")
    @Transient
    private String authority;

    @ApiModelProperty(value = "菜单权限名称，多个用逗号隔开")
    @Transient
    private String authorityName;

    @ApiModelProperty(value = "父菜单名称")
    @Transient
    private String parentName;

    @ApiModelProperty(value = "菜单权限数组")
    @Transient
    private String[] authorities;
}