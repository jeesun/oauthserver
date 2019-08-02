package com.simon.model;

import com.simon.common.domain.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
* t_s_town
* @author SimonSun
* @date 2019-04-25
**/
@ApiModel(description = "t_s_town")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_s_town")
public class Town extends BasePo<Integer> implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ApiModelProperty(value = "name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "town_id")
    @Column(name = "town_id")
    private String townId;

    @ApiModelProperty(value = "country_id")
    @Column(name = "country_id")
    private String countryId;

    @ApiModelProperty(value = "创建人名称")
    @Transient
    private String createUser;

    @ApiModelProperty(value = "更新人名称")
    @Transient
    private String updateUser;
}