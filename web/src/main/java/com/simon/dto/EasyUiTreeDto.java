package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI Tree节点
 *
 * @author simon
 * @date 2019-01-12
 **/
@ApiModel(description = "EasyUI Tree节点")
@Data
@EqualsAndHashCode(callSuper = false)
public class EasyUiTreeDto implements Serializable {
    private static final long serialVersionUID = -6956516610093262687L;

    private Long pid;

    private Long id;

    private String text;

    @ApiModelProperty(value = "权限")
    private String authority;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "关联id")
    private Long linkId;

    private Boolean checked = false;

    @ApiModelProperty(value = "默认状态[closed,open]")
    private String state = "open";

    private List<EasyUiTreeDto> children;
}
