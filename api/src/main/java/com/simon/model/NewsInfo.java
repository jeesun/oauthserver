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
import java.time.LocalDateTime;

/**
* @author SimonSun
* @create 2018-09-12
**/
@ApiModel(value = "t_news_info")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_news_info")
public class NewsInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
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
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "标题")
    @Column(name = "title")
    private String title;

    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "状态")
    @Column(name = "status")
    private Integer status;

    @ApiModelProperty(value = "新闻内容")
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ApiModelProperty(value = "缩略图")
    @Column(name = "image_url")
    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "发布时间")
    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @ApiModelProperty(value = "标签")
    @Column(name = "tags")
    private String tags;

    @ApiModelProperty(value = "新闻类型")
    @Column(name = "news_type")
    private Integer newsType;
}