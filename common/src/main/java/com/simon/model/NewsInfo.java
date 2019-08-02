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
* 新闻
* @author SimonSun
* @date 2019-01-20
**/
@ApiModel(description = "新闻")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_news_info")
public class NewsInfo extends BasePo<Long> implements Serializable{
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

    @ApiModelProperty(value = "标题")
    @Column(name = "title")
    private String title;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "状态")
    @Column(name = "news_status")
    private Integer newsStatus;

    @ApiModelProperty(value = "新闻内容")
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ApiModelProperty(value = "缩略图")
    @Column(name = "image_url")
    private String imageUrl;

    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
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

    @ApiModelProperty(value = "创建人名称")
    @Transient
    private String createUser;

    @ApiModelProperty(value = "更新人名称")
    @Transient
    private String updateUser;
}