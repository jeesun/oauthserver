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
* @create 2018-08-17 23:33:01
**/
@ApiModel(description = "QrCode")
@Data
@Entity
@Table(name="qr_code")
public class QrCode implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "is_ok")
    @Column(name = "is_ok")
    private Boolean isOk;

    @ApiModelProperty(value = "sid")
    @Column(name = "sid")
    private String sid;

    @ApiModelProperty(value = "token")
    @Column(name = "token")
    private String token;

    @ApiModelProperty(value = "username")
    @Column(name = "username")
    private String username;
}