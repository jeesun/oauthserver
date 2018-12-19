package com.simon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simon.common.config.AppConfig;
import com.simon.common.utils.UUIdGenId;
import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* 账号绑定
* @author SimonSun
* @date 2018-12-04
**/
@ApiModel(description = "账号绑定")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_account_bind")
public class AccountBind implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
@ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private Date createDate;

    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
@ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private Date updateDate;

    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "账号类型")
    @Column(name = "account_type")
    private Integer accountType;

    @ApiModelProperty(value = "账号")
    @Column(name = "account_no")
    private String accountNo;

    @ApiModelProperty(value = "密码")
    @Column(name = "password")
    private String password;

    @ApiModelProperty(value = "绑定密钥")
    @Column(name = "secret_key")
    private String secretKey;

    @ApiModelProperty(value = "是否绑定")
    @Column(name = "is_bind")
    private Byte[] isBind;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
@ApiModelProperty(value = "绑定过期时间")
    @Column(name = "overdue_time")
    private Date overdueTime;

    @ApiModelProperty(value = "状态")
    @Column(name = "status")
    private String status;
}