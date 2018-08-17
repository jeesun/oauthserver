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
@ApiModel(description = "VeriCode")
@Data
@Entity
@Table(name="veri_code")
public class VeriCode implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "code")
    @Column(name = "code")
    private Integer code;

    @ApiModelProperty(value = "create_time")
    @Column(name = "create_time")
    private Long createTime;

    @ApiModelProperty(value = "expires")
    @Column(name = "expires")
    private Integer expires;

    @ApiModelProperty(value = "phone")
    @Column(name = "phone")
    private String phone;
}