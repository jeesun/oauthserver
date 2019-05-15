package com.simon.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.simon.common.config.AppConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* @author SimonSun
* @date 2018-09-12
**/
@ApiModel(description = "用户")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_users")
public class OauthUser extends BaseRowModel implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final String TABLE_TITLE = "用户";

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ExcelProperty(value = {TABLE_TITLE, "id"}, index = 0)
    @ApiModelProperty(value = "id")
    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ExcelProperty(value = {TABLE_TITLE, "创建人id"}, index = 1)
    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @ExcelProperty(value = {TABLE_TITLE, "创建时间"}, index = 2, format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ExcelProperty(value = {TABLE_TITLE, "更新人id"}, index = 3)
    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @ExcelProperty(value = {TABLE_TITLE, "更新时间"}, index = 4, format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ExcelProperty(value = {TABLE_TITLE, "用户名(昵称)"}, index = 5)
    @ApiModelProperty(value = "用户名(昵称)")
    @Column(name = "username", nullable = false)
    private String username;

    @ExcelProperty(value = {TABLE_TITLE, "密码"}, index = 6)
    @ApiModelProperty(value = "密码")
    @Column(name = "password", nullable = false)
    private String password;

    @ExcelProperty(value = {TABLE_TITLE, "有效"}, index = 7)
    @ApiModelProperty(value = "有效")
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ExcelProperty(value = {TABLE_TITLE, "手机区号"}, index = 8)
    @ApiModelProperty(value = "手机区号")
    @Column(name = "area_code")
    private String areaCode;

    @ExcelProperty(value = {TABLE_TITLE, "手机号"}, index = 9)
    @ApiModelProperty(value = "手机号")
    @Column(name = "phone")
    private String phone;

    @ExcelProperty(value = {TABLE_TITLE, "邮箱"}, index = 10)
    @ApiModelProperty(value = "邮箱")
    @Column(name = "email")
    private String email;

    @ExcelProperty(value = {TABLE_TITLE, "地址"}, index = 11)
    @ApiModelProperty(value = "地址")
    @Column(name = "address")
    private String address;

    @ExcelProperty(value = {TABLE_TITLE, "年龄"}, index = 12)
    @ApiModelProperty(value = "年龄")
    @Column(name = "age")
    private Integer age;

    @ExcelProperty(value = {TABLE_TITLE, "生日"}, index = 13, format = AppConfig.DATE_PATTERN_DAY)
    @DateTimeFormat(pattern = AppConfig.DATE_PATTERN_DAY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DAY, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "生日")
    @Column(name = "birth")
    private LocalDate birth;

    @ExcelProperty(value = {TABLE_TITLE, "头像"}, index = 14)
    @ApiModelProperty(value = "头像")
    @Column(name = "head_photo")
    private String headPhoto;

    @ExcelProperty(value = {TABLE_TITLE, "个人简介"}, index = 15)
    @ApiModelProperty(value = "个人简介")
    @Column(name = "person_brief")
    private String personBrief;

    @ExcelProperty(value = {TABLE_TITLE, "性别[0:女,1:男]"}, index = 16)
    @ApiModelProperty(value = "性别[0:女,1:男]")
    @Column(name = "sex")
    private Boolean sex;

    @ExcelProperty(value = {TABLE_TITLE, "邀请码"}, index = 17)
    @ApiModelProperty(value = "邀请码")
    @Column(name = "visit_card")
    private String visitCard;

    @ExcelProperty(value = {TABLE_TITLE, "登录方式"}, index = 18)
    @ApiModelProperty(value = "登录方式")
    @Column(name = "login_type")
    private Integer loginType;

    @ExcelProperty(value = {TABLE_TITLE, "登录状态"}, index = 19)
    @ApiModelProperty(value = "登录状态")
    @Column(name = "login_status")
    private String loginStatus;

    @ExcelProperty(value = {TABLE_TITLE, "登录时间"}, index = 20, format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "登录时间")
    @Column(name = "login_date")
    private LocalDateTime loginDate;

    @ApiModelProperty(value = "角色")
    @Transient
    private String authorities;

    @ApiModelProperty(value = "创建人名称")
    @Transient
    private String createUser;

    @ApiModelProperty(value = "更新人名称")
    @Transient
    private String updateUser;
}