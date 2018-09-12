package com.simon.model;

import com.simon.common.utils.UUIdGenId;
import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* @author SimonSun
* @create 2018-09-12
**/
@ApiModel(description = "OauthUser")
@Data
@Entity
@Table(name="t_users")
public class OauthUser implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private Date createDate;

    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private Date updateDate;

    @ApiModelProperty(value = "用户名")
    @Column(name = "username", nullable = false)
    private String username;

    @ApiModelProperty(value = "密码")
    @Column(name = "password", nullable = false)
    private String password;

    @ApiModelProperty(value = "有效")
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ApiModelProperty(value = "手机号")
    @Column(name = "phone")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @Column(name = "email")
    private String email;

    @ApiModelProperty(value = "oppo账号ssoid")
    @Column(name = "ssoid")
    private String ssoid;

    @ApiModelProperty(value = "播放列表id")
    @Column(name = "album_id")
    private Long albumId;

    @ApiModelProperty(value = "地址")
    @Column(name = "address")
    private String address;

    @ApiModelProperty(value = "年龄")
    @Column(name = "age")
    private Integer age;

    @ApiModelProperty(value = "生日")
    @Column(name = "birth")
    private String birth;

    @ApiModelProperty(value = "头像")
    @Column(name = "head_photo")
    private String headPhoto;

    @ApiModelProperty(value = "个人简介")
    @Column(name = "person_brief")
    private String personBrief;

    @ApiModelProperty(value = "性别")
    @Column(name = "sex")
    private Boolean sex;

    @ApiModelProperty(value = "邀请码")
    @Column(name = "visit_card")
    private String visitCard;

    @ApiModelProperty(value = "登录方式")
    @Column(name = "login_type")
    private Integer loginType;

    @ApiModelProperty(value = "登录状态")
    @Column(name = "login_status")
    private String loginStatus;

    @ApiModelProperty(value = "登录时间")
    @Column(name = "login_date")
    private Date loginDate;
}