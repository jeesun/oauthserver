package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author simon
 * @date 2019-05-05
 */
@ApiModel(description = "修改密码Dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class ChangePasswordDto implements Serializable {
    private static final long serialVersionUID = 735228348370650679L;

    @ApiModelProperty(value = "旧密码")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

    @ApiModelProperty(value = "重复新密码")
    private String repeatNewPassword;
}