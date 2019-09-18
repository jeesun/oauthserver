package com.simon.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
* quartz任务
* @author SimonSun
* @date 2018-12-22
**/
@ApiModel(description = "quartz任务")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_s_quartz_job")
public class QuartzJob extends BasePo<Long> implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @JSONField(serializeUsing = ToStringSerializer.class)
    @Id
    @Column(name = "id")
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @JsonSerialize(using = ToStringSerializer.class)
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "cron表达式", example = "*/5 * * * * ?")
    @Column(name = "cron_expression")
    private String cronExpression;

    @ApiModelProperty(value = "描述")
    @Column(name = "description")
    private String description;

    @ApiModelProperty(value = "完全限定类名", example = "com.simon.task.CtripScenicJob")
    @Column(name = "bean_name")
    private String beanName;

    @ApiModelProperty(value = "触发器名称", example = "org.quartz.CronTrigger")
    @Column(name = "trigger_name")
    private String triggerName;

    @ApiModelProperty(value = "任务状态")
    @Column(name = "job_status")
    private Integer jobStatus;

    @ApiModelProperty(value = "任务名", example = "com.simon.task.CtripScenicJob")
    @Column(name = "job_name")
    private String jobName;

    @ApiModelProperty(value = "任务组", example = "group01")
    @Column(name = "job_group")
    private String jobGroup;

    @ApiModelProperty(value = "创建人名称")
    @Transient
    private String createUser;

    @ApiModelProperty(value = "更新人名称")
    @Transient
    private String updateUser;
}