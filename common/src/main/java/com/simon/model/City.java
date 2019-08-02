package com.simon.model;

import com.simon.common.domain.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
* t_s_city
* @author SimonSun
* @date 2019-04-24
**/
@ApiModel(description = "城市")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_s_city")
public class City extends BasePo<Integer> implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ApiModelProperty(value = "name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "city_id")
    @Column(name = "city_id")
    private String cityId;

    @ApiModelProperty(value = "province_id")
    @Column(name = "province_id")
    private String provinceId;

    @ApiModelProperty(value = "区")
    @Transient
    private Country[] children;
}