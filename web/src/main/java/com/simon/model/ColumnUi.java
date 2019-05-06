package com.simon.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
* 属性UI
* @author SimonSun
* @date 2019-05-03
**/
@ApiModel(description = "属性UI")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_s_column_ui")
public class ColumnUi implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "表名")
    @Column(name = "table_name", nullable = false)
    private String tableName;

    @ApiModelProperty(value = "属性名")
    @Column(name = "name", nullable = false)
    private String name;

    @ApiModelProperty(value = "属性UI类型")
    @Column(name = "ui_type", nullable = false)
    private String uiType;
}