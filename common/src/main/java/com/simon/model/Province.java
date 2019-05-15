package com.simon.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
* t_s_province
* @author SimonSun
* @date 2019-04-24
**/
@ApiModel(description = "省")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_s_province")
public class Province implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ApiModelProperty(value = "name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "province_id")
    @Column(name = "province_id")
    private String provinceId;

    @ApiModelProperty(value = "城市")
    @Transient
    private City[] children;
}