package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI TreeGrid
 *
 * @author simon
 * @date 2018-11-13
 **/
@ApiModel(description = "字典")
@Data
@EqualsAndHashCode(callSuper = false)
public class EasyUiTreeGridDto implements Serializable {
    private static final long serialVersionUID = -6124138315161529007L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "菜单组id")
    private Long pid;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "默认状态[closed,open]")
    private String state;

    private List<EasyUiTreeGridDto> children;
}
