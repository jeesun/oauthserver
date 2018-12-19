package com.simon.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
* logging_event
* @author SimonSun
* @date 2018-11-09
**/
@ApiModel(description = "logging_event")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="logging_event")
public class LoggingEvent implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "时间")
    @Column(name = "timestmp", nullable = false)
    private Long timestmp;

    @ApiModelProperty(value = "格式化信息")
    @Column(name = "formatted_message", nullable = false, columnDefinition = "TEXT")
    private String formattedMessage;

    @ApiModelProperty(value = "日志名称")
    @Column(name = "logger_name", nullable = false)
    private String loggerName;

    @ApiModelProperty(value = "日志等级")
    @Column(name = "level_string", nullable = false)
    private String levelString;

    @ApiModelProperty(value = "线程名称")
    @Column(name = "thread_name")
    private String threadName;

    @ApiModelProperty(value = "参考标记")
    @Column(name = "reference_flag")
    private Short referenceFlag;

    @ApiModelProperty(value = "参数0")
    @Column(name = "arg0")
    private String arg0;

    @ApiModelProperty(value = "参数1")
    @Column(name = "arg1")
    private String arg1;

    @ApiModelProperty(value = "参数2")
    @Column(name = "arg2")
    private String arg2;

    @ApiModelProperty(value = "参数3")
    @Column(name = "arg3")
    private String arg3;

    @ApiModelProperty(value = "调用文件名称")
    @Column(name = "caller_filename", nullable = false)
    private String callerFilename;

    @ApiModelProperty(value = "调用类")
    @Column(name = "caller_class", nullable = false)
    private String callerClass;

    @ApiModelProperty(value = "调用方法")
    @Column(name = "caller_method", nullable = false)
    private String callerMethod;

    @ApiModelProperty(value = "调用行号")
    @Column(name = "caller_line", nullable = false, columnDefinition ="char(4)")
    private String callerLine;

    @Id
    @ApiModelProperty(value = "事件id")
    @Column(name = "event_id", nullable = false)
    private Long eventId;
}