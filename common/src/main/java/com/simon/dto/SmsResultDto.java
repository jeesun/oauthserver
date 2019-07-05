package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author simon
 * @version 1.0
 * @date 2019-07-05 15:14
 */
@ApiModel(description = "短信结果dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class SmsResultDto implements Serializable {
    private static final long serialVersionUID = 5182432718131749804L;

    @ApiModelProperty(value = "请求结果")
    private Boolean result;

    @ApiModelProperty(value = "验证码值")
    private String code;

    @ApiModelProperty(value = "错误信息")
    private String errMsg;
}
