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
* @create 2018-09-12
**/
@ApiModel(value = "t_reset_pwd_info")
@Data
@Entity
@Table(name="t_reset_pwd_info")
public class ResetPwdInfo implements Serializable{
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