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
* @date 2018-09-06 10:03:50
**/
@ApiModel(description = "DictTypeGroup")
@Data
@Entity
@Table(name="t_dict_type_group")
public class DictTypeGroup implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.boya.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private Date createDate;

    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private Date updateDate;

    @ApiModelProperty(value = "字典组编码")
    @Column(name = "type_group_code", nullable = false)
    private String typeGroupCode;

    @ApiModelProperty(value = "字典组名称")
    @Column(name = "type_group_name", nullable = false)
    private String typeGroupName;
}