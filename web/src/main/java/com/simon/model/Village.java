package com.simon.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
* t_s_village
* @author SimonSun
* @date 2019-04-24
**/
@ApiModel(description = "t_s_village")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_s_village")
public class Village implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ApiModelProperty(value = "name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "village_id")
    @Column(name = "village_id")
    private String villageId;

    @ApiModelProperty(value = "town_id")
    @Column(name = "town_id")
    private String townId;
}