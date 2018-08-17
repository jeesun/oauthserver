package com.simon.model;

import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* @author SimonSun
* @create 2018-08-17 23:33:01
**/
@ApiModel(description = "ResetPwdInfo")
@Data
@Entity
@Table(name="reset_pwd_info")
public class ResetPwdInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "expires_in")
    @Column(name = "expires_in")
    private Date expiresIn;

    @ApiModelProperty(value = "secret_key")
    @Column(name = "secret_key")
    private String secretKey;

    @ApiModelProperty(value = "user_id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "valid")
    @Column(name = "valid", nullable = false)
    private Boolean valid;
}