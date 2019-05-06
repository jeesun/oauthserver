package com.simon.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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

    @JSONField(serializeUsing = ToStringSerializer.class)
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

    @ApiModelProperty(value = "菜单权限，多个用逗号隔开")
    private String authority;

    @ApiModelProperty(value = "菜单权限名称，多个用逗号隔开")
    private String authorityName;

    @ApiModelProperty(value = "菜单地址")
    private String url;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "菜单组id")
    private Long pid;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单类型[1:一级菜单, 2:二级菜单, 3:三级菜单]")
    private Integer menuType;

    @ApiModelProperty(value = "子菜单列表")
    private List<EasyUiSideMenuDto> children;

    @ApiModelProperty(value = "默认状态[closed,open]")
    private String state;

    @ApiModelProperty(value = "请求方式")
    private String requestMethod;
}
