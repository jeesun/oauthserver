package com.simon.model;

import com.simon.common.utils.UUIdGenId;
import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* 侧边菜单
* @author SimonSun
* @date 2018-09-26
**/
@ApiModel(value = "侧边菜单")
@Data
@Entity
@Table(name="t_side_menu")
public class SideMenu implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @KeySql(genId = SnowflakeGenId.class)
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
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

    @ApiModelProperty(value = "菜单名称")
    @Column(name = "menu_name")
    private String menuName;

    @ApiModelProperty(value = "菜单地址")
    @Column(name = "menu_url")
    private String menuUrl;

    @ApiModelProperty(value = "菜单权限")
    @Column(name = "menu_authority")
    private String menuAuthority;

    @ApiModelProperty(value = "菜单图标")
    @Column(name = "menu_icon")
    private String menuIcon;

    @ApiModelProperty(value = "菜单组id")
    @Column(name = "menu_group_id")
    private Long menuGroupId;

    @ApiModelProperty(value = "排序")
    @Column(name = "order_num")
    private Integer orderNum;
}