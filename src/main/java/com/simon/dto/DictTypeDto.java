package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 字典
 *
 * @author simon
 * @date 2018-10-09
 **/

@ApiModel(description = "字典")
@Data
@EqualsAndHashCode(callSuper = false)
public class DictTypeDto implements Serializable {
    private static final long serialVersionUID = -1182326226911558832L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "字典code")
    private String code;

    @ApiModelProperty(value = "父字典id")
    private String pid;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "字典类型[1:1级菜单, 2:2级菜单]")
    private Integer type;
}
