package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限dto
 *
 * @author simon
 * @date 2018-11-14
 **/
@ApiModel(description = "权限dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorityDto {
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名(昵称)")
    private String username;

    @ApiModelProperty(value = "权限组")
    private String authority;
}
