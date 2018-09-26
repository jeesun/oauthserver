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
* 侧边菜单组
* @author SimonSun
* @date 2018-09-26
**/
@ApiModel(value = "侧边菜单组")
@Data
@Entity
@Table(name="t_side_menu_group")
public class SideMenuGroup implements Serializable{
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

    @ApiModelProperty(value = "菜单组名称")
    @Column(name = "menu_group_name")
    private String menuGroupName;

    @ApiModelProperty(value = "菜单组权限")
    @Column(name = "menu_group_authority")
    private String menuGroupAuthority;

    @ApiModelProperty(value = "菜单组图标")
    @Column(name = "menu_group_icon")
    private String menuGroupIcon;

    @ApiModelProperty(value = "排序")
    @Column(name = "order_num")
    private String orderNum;
}