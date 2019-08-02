package com.simon.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.simon.common.config.AppConfig;
import com.simon.common.domain.BasePo;
import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class AccountBind extends BasePo<Long> implements Serializable{
    private static final long serialVersionUID = 1L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @Id
    @Column(name = "id")
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
@ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
@ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @JSONField(serializeUsing = ToStringSerializer.class)
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
    private Boolean isBind;

    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
@ApiModelProperty(value = "绑定过期时间")
    @Column(name = "overdue_time")
    private LocalDateTime overdueTime;

    @ApiModelProperty(value = "状态")
    @Column(name = "status")
    private String status;
}