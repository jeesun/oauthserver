package com.simon.model;

import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author SimonSun
* @create 2018-08-17 23:33:01
**/
@ApiModel(description = "OauthUser")
@Data
@Entity
@Table(name="users")
public class OauthUser implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "username")
    @Column(name = "username", nullable = false)
    private String username;

    @ApiModelProperty(value = "password")
    @Column(name = "password", nullable = false)
    private String password;

    @ApiModelProperty(value = "enabled")
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ApiModelProperty(value = "email")
    @Column(name = "email")
    private String email;

    @ApiModelProperty(value = "phone")
    @Column(name = "phone")
    private String phone;

    @ApiModelProperty(value = "enable")
    @Column(name = "enable")
    private Boolean enable;

    @ApiModelProperty(value = "address")
    @Column(name = "address")
    private String address;

    @ApiModelProperty(value = "age")
    @Column(name = "age")
    private Integer age;

    @ApiModelProperty(value = "birth")
    @Column(name = "birth")
    private String birth;

    @ApiModelProperty(value = "head_photo")
    @Column(name = "head_photo")
    private String headPhoto;

    @ApiModelProperty(value = "person_brief")
    @Column(name = "person_brief")
    private String personBrief;

    @ApiModelProperty(value = "sex")
    @Column(name = "sex")
    private Boolean sex;

    @ApiModelProperty(value = "visit_card")
    @Column(name = "visit_card")
    private String visitCard;
}