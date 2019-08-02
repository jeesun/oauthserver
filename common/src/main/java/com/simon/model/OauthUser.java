package com.simon.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.liaochong.myexcel.core.WorkbookType;
import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import com.github.liaochong.myexcel.core.annotation.ExcelTable;
import com.github.liaochong.myexcel.core.annotation.ExcludeColumn;
import com.simon.common.config.AppConfig;
import com.simon.common.domain.BasePo;
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
@ExcelTable(sheetName = "用户", workbookType = WorkbookType.SXLSX, rowAccessWindowSize = 100, useFieldNameAsTitle = true)
@ApiModel(description = "用户")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_users")
public class OauthUser extends BasePo<Long> implements Serializable{
    @ExcludeColumn
    private static final long serialVersionUID = 4898481229724057581L;

    @ExcelColumn(title = "id", index = 0, convertToString = true)
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "id")
    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExcludeColumn
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @ExcludeColumn
    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ExcludeColumn
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @ExcludeColumn
    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ExcelColumn(title = "用户名", index = 1)
    @ApiModelProperty(value = "用户名(昵称)")
    @Column(name = "username", nullable = false)
    private String username;

    @ExcludeColumn
    @ApiModelProperty(value = "密码")
    @Column(name = "password", nullable = false)
    private String password;

    @ExcelColumn(title = "账号状态", index = 2)
    @ApiModelProperty(value = "账号状态")
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ExcelColumn(title = "手机区号", index = 3)
    @ApiModelProperty(value = "手机区号")
    @Column(name = "area_code")
    private String areaCode;

    @ExcelColumn(title = "手机号", index = 4)
    @ApiModelProperty(value = "手机号")
    @Column(name = "phone")
    private String phone;

    @ExcelColumn(title = "邮箱", index = 5)
    @ApiModelProperty(value = "邮箱")
    @Column(name = "email")
    private String email;

    @ExcelColumn(title = "地址", index = 6)
    @ApiModelProperty(value = "地址")
    @Column(name = "address")
    private String address;

    @ExcelColumn(title = "年龄", index = 7)
    @ApiModelProperty(value = "年龄")
    @Column(name = "age")
    private Integer age;

    @ExcelColumn(title = "生日", index = 8, dateFormatPattern = AppConfig.DATE_PATTERN_DAY)
    @JSONField(format = AppConfig.DATE_PATTERN_DAY)
    @DateTimeFormat(pattern = AppConfig.DATE_PATTERN_DAY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DAY, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "生日")
    @Column(name = "birth")
    private LocalDate birth;

    @ExcelColumn(title = "头像", index = 9)
    @ApiModelProperty(value = "头像")
    @Column(name = "head_photo")
    private String headPhoto;

    @ExcelColumn(title = "个人简介", index = 10)
    @ApiModelProperty(value = "个人简介")
    @Column(name = "person_brief")
    private String personBrief;

    @ExcelColumn(title = "性别", index = 11)
    @ApiModelProperty(value = "性别[0:女,1:男]")
    @Column(name = "sex")
    private Boolean sex;

    @ExcludeColumn
    @ApiModelProperty(value = "邀请码")
    @Column(name = "visit_card")
    private String visitCard;

    @ExcludeColumn
    @ApiModelProperty(value = "登录方式")
    @Column(name = "login_type")
    private Integer loginType;

    @ExcludeColumn
    @ApiModelProperty(value = "登录状态")
    @Column(name = "login_status")
    private String loginStatus;

    @ExcludeColumn
    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "登录时间")
    @Column(name = "login_date")
    private LocalDateTime loginDate;

    @ExcludeColumn
    @ApiModelProperty(value = "角色")
    @Transient
    private String authorities;

    @ExcludeColumn
    @ApiModelProperty(value = "创建人名称")
    @Transient
    private String createUser;

    @ExcludeColumn
    @ApiModelProperty(value = "更新人名称")
    @Transient
    private String updateUser;
}