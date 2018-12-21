package com.simon.model;

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
import java.util.Date;

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
public class QuartzJob implements Serializable{
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

    @ApiModelProperty(value = "cron表达式")
    @Column(name = "cron_expression")
    private String cronExpression;

    @ApiModelProperty(value = "任务调用的方法名")
    @Column(name = "method_name")
    private String methodName;

    @ApiModelProperty(value = "任务是否有状态")
    @Column(name = "is_concurrent")
    private Integer isConcurrent;

    @ApiModelProperty(value = "描述")
    @Column(name = "description")
    private String description;

    @ApiModelProperty(value = "任务执行时调用哪个类的方法 包名+类名，完全限定名")
    @Column(name = "bean_name")
    private String beanName;

    @ApiModelProperty(value = "触发器名称")
    @Column(name = "trigger_name")
    private String triggerName;

    @ApiModelProperty(value = "任务状态")
    @Column(name = "job_status")
    private Integer jobStatus;

    @ApiModelProperty(value = "spring_bean")
    @Column(name = "spring_bean")
    private String springBean;

    @ApiModelProperty(value = "任务名")
    @Column(name = "job_name")
    private String jobName;
}