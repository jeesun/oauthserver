package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 按钮权限Dto
 *
 * @author simon
 * @date 2019-01-11
 **/
@ApiModel(description = "按钮权限Dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class ButtonAuthorityDto {
    @ApiModelProperty(value = "权限")
    private String authority;

    @ApiModelProperty(value = "备注【按钮备注add、edit、delete】")
    private String remark;
}
