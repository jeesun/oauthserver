package com.simon.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
* t_s_country
* @author SimonSun
* @date 2019-04-24
**/
@ApiModel(description = "t_s_country")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_s_country")
public class Country implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ApiModelProperty(value = "name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "country_id")
    @Column(name = "country_id")
    private String countryId;

    @ApiModelProperty(value = "city_id")
    @Column(name = "city_id")
    private String cityId;
}