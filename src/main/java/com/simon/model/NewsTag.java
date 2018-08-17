package com.simon.model;

import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author SimonSun
* @create 2018-08-18 00:41:52
**/
@ApiModel(description = "NewsTag")
@Data
@Entity
@Table(name="news_tag")
public class NewsTag implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "news_info_id")
    @Column(name = "news_info_id")
    private Long newsInfoId;

    @ApiModelProperty(value = "tag_id")
    @Column(name = "tag_id")
    private Long tagId;
}