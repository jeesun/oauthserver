package com.simon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simon.common.config.AppConfig;
import com.simon.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* 订单
* @author SimonSun
* @date 2018-11-20
**/
@ApiModel(description = "订单")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="t_bill")
public class Bill implements Serializable{
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
@ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private Date createDate;

    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
@ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private Date updateDate;

    @ApiModelProperty(value = "账单分类")
    @Column(name = "bill_type")
    private String billType;

    @ApiModelProperty(value = "账单状态")
    @Column(name = "bill_status")
    private String billStatus;

    @ApiModelProperty(value = "账单描述(商品描述)")
    @Column(name = "bill_desc")
    private String billDesc;

    @ApiModelProperty(value = "收货地址")
    @Column(name = "receiving_address")
    private String receivingAddress;

    @ApiModelProperty(value = "物流信息")
    @Column(name = "logistics_info")
    private String logisticsInfo;

    @ApiModelProperty(value = "物流号")
    @Column(name = "logistics_no")
    private String logisticsNo;

    @ApiModelProperty(value = "物流状况")
    @Column(name = "logistics_status")
    private String logisticsStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
@ApiModelProperty(value = "账单日期")
    @Column(name = "bill_date")
    private Date billDate;

    @ApiModelProperty(value = "付款金额", notes = "微信单位是分，支付宝单位是元")
    @Column(name = "total_amount")
    private Double totalAmount;

    @ApiModelProperty(value = "数量")
    @Column(name = "quantity")
    private Integer quantity;

    @ApiModelProperty(value = "商户订单号")
    @Column(name = "out_trade_no")
    private String outTradeNo;

    @ApiModelProperty(value = "对方账号名称")
    @Column(name = "to_member_name")
    private String toMemberName;

    @ApiModelProperty(value = "对方账号")
    @Column(name = "to_member_id")
    private String toMemberId;

    @ApiModelProperty(value = "付款方式")
    @Column(name = "payment_type")
    private String paymentType;

    @ApiModelProperty(value = "付款账号")
    @Column(name = "payment_account_no")
    private String paymentAccountNo;

    @ApiModelProperty(value = "理由")
    @Column(name = "reason")
    private String reason;

    @ApiModelProperty(value = "积分奖励")
    @Column(name = "integral_reward")
    private Integer integralReward;

    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty(value = "付款详情页的订单信息")
    @Column(name = "bill_context")
    private String billContext;

    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "用户名(昵称)")
    @Transient
    private String username;
}