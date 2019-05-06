package com.simon.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "权限组，多个用逗号隔开")
    private String authority;

    @ApiModelProperty(value = "权限组")
    private String[] authorities;

    @ApiModelProperty(value = "权限组名称，多个用逗号隔开")
    private String authorityName;
}
