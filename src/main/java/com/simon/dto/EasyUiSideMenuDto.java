package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI侧边栏菜单
 *
 * @author simon
 * @date 2018-10-13
 **/

@ApiModel(description = "EasyUI侧边栏菜单")
@Data
@EqualsAndHashCode(callSuper = false)
public class EasyUiSideMenuDto implements Serializable {
    private static final long serialVersionUID = 1935007000221050105L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单名称")
    private String text;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单图标")
    private String iconCls;

    @ApiModelProperty(value = "菜单权限")
    private String authority;

    @ApiModelProperty(value = "菜单地址")
    private String url;

    @ApiModelProperty(value = "菜单组id")
    private Long pid;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单类型")
    private Integer menuType;

    @ApiModelProperty(value = "子菜单列表")
    private List<EasyUiSideMenuDto> children;

    @ApiModelProperty(value = "默认状态")
    private String state;
}
