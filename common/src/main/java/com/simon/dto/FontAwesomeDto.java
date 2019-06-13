package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Font Awesome字体图标dto
 *
 * @author simon
 * @date 2019-04-29
 **/
@ApiModel(description = "Font Awesome字体图标dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class FontAwesomeDto implements Serializable {
    private static final long serialVersionUID = 2648710952176776629L;

    @ApiModelProperty(value = "图标class")
    private String iconClass;

    @ApiModelProperty(value = "标签")
    private String label;
}