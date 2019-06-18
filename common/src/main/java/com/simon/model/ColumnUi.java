package com.simon.model;

import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 属性UI
 *
 * @author SimonSun
 * @date 2019-05-03
 **/
@ApiModel(description = "属性UI")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "t_s_column_ui")
public class ColumnUi implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    @ApiModelProperty(value = "表名")
    @Column(name = "table_name", nullable = false)
    private String tableName;

    @ApiModelProperty(value = "属性名")
    @Column(name = "name", nullable = false)
    private String name;

    @ApiModelProperty(value = "属性UI类型")
    @Column(name = "ui_type", nullable = false)
    private String uiType;

    @ApiModelProperty(value = "补充信息")
    @Column(name = "extra_info")
    private String extraInfo;

    @ApiModelProperty(value = "允许搜索")
    @Column(name = "allow_search")
    private Boolean allowSearch;

    @ApiModelProperty(value = "是否隐藏")
    @Column(name = "hidden")
    private Boolean hidden;

    @ApiModelProperty(value = "允许输入")
    @Column(name = "allow_input")
    private Boolean allowInput;
}