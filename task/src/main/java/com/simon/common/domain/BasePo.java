package com.simon.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author simon
 * @version 1.0
 * @date 2019-07-27 15:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BasePo<ID extends java.io.Serializable> implements PO<ID> {
/*
    @ExcludeColumn
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人id")
    @Column(name = "create_by")
    private Long createBy;

    @ExcludeColumn
    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ExcludeColumn
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "更新人id")
    @Column(name = "update_by")
    private Long updateBy;

    @ExcludeColumn
    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_date")
    private LocalDateTime updateDate;*/
}
