package com.simon.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.simon.common.config.AppConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author simon
 * @version 1.0
 * @date 2019-07-09 9:28
 */
@ApiModel(description = "用户dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class OauthUserDto implements Serializable {
    private static final long serialVersionUID = -4619525018151286048L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "生日")
    @JSONField(format = AppConfig.DATE_PATTERN_DAY)
    @DateTimeFormat(pattern = AppConfig.DATE_PATTERN_DAY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DAY, timezone = AppConfig.DATE_TIMEZONE)
    private LocalDate birth;

    @ApiModelProperty(value = "头像")
    private String headPhoto;

    @ApiModelProperty(value = "个人简介")
    private String personBrief;

    @ApiModelProperty(value = "性别")
    private Boolean sex;
}
