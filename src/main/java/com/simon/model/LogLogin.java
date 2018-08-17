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
@ApiModel(description = "LogLogin")
@Data
@Entity
@Table(name="log_login")
public class LogLogin implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "create_time")
    @Column(name = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "ip")
    @Column(name = "ip")
    private String ip;

    @ApiModelProperty(value = "username")
    @Column(name = "username")
    private String username;
}