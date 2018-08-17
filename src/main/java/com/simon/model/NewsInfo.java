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
@ApiModel(description = "NewsInfo")
@Data
@Entity
@Table(name="news_info")
public class NewsInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "title")
    @Column(name = "title")
    private String title;

    @ApiModelProperty(value = "user_id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "status")
    @Column(name = "status")
    private Integer status;

    @ApiModelProperty(value = "content")
    @Column(name = "content")
    private String content;

    @ApiModelProperty(value = "image_url")
    @Column(name = "image_url")
    private String imageUrl;

    @ApiModelProperty(value = "publish_date")
    @Column(name = "publish_date")
    private Date publishDate;

    @ApiModelProperty(value = "tags")
    @Column(name = "tags")
    private String tags;
}