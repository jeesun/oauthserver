package com.simon.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.simon.common.config.AppConfig;
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
* @author SimonSun
* @create 2018-09-12
**/
@ApiModel(value = "t_reset_pwd_info")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_reset_pwd_info")
public class ResetPwdInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @Id
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

    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "expires_in")
    @Column(name = "expires_in")
    private LocalDateTime expiresIn;

    @ApiModelProperty(value = "secret_key")
    @Column(name = "secret_key")
    private String secretKey;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "user_id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "valid")
    @Column(name = "valid", nullable = false)
    private Boolean valid;
}