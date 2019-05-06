package com.simon.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单
 *
 * @author simon
 * @date 2018-11-08
 **/
@ApiModel(description = "菜单")
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuDO implements Serializable {
    private static final long serialVersionUID = -6743756021695562306L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单URL")
    private String url;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    @ApiModelProperty(value = "类型 0：目录 1：菜单 2：按钮")
    private Integer type;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;
}
