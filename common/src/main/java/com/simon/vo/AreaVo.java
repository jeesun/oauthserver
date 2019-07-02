package com.simon.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author simon
 * @version 1.0
 * @date 2019-06-28 10:59
 */
@ApiModel(description = "省市区Vo")
@Data
@EqualsAndHashCode(callSuper = false)
public class AreaVo implements Serializable {
    private static final long serialVersionUID = 5584008943036682341L;

    @ApiModelProperty(value = "省id")
    private String provinceId;

    @ApiModelProperty(value = "市id")
    private String cityId;

    @ApiModelProperty(value = "区id")
    private String countryId;

    @ApiModelProperty(value = "省名")
    private String provinceName;

    @ApiModelProperty(value = "市名")
    private String cityName;

    @ApiModelProperty(value = "区名")
    private String countryName;
}
