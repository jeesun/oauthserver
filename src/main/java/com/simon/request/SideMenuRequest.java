package com.simon.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 侧栏菜单请求
 *
 * @author simon
 * @date 2018-09-30
 **/
@ApiModel(description = "侧栏菜单请求")
@Data
@EqualsAndHashCode(callSuper = false)
public class SideMenuRequest implements Serializable {
    private static final long serialVersionUID = -4426844041074151750L;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单权限")
    private String authority;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单组id")
    private Long menuGroupId;

    @ApiModelProperty(value = "菜单类型[1:一级菜单, 2:二级菜单]")
    private Integer menuType;
}
