package com.simon.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@ApiModel(value = "OauthUser", description = "用户")
@Data
@Table(name = "users")
@Entity
public class OauthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "是否有效")
    private Boolean enabled;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "生日")
    private String birth;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "头像")
    private String headPhoto;

    @ApiModelProperty(value = "个人简介")
    private String personBrief;

    @ApiModelProperty(value = "性别")
    private Boolean sex;

    @ApiModelProperty(value = "邀请码")
    private String visitCard;
}