package ${basePackage}.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import ${basePackage}.common.config.AppConfig;
import ${basePackage}.common.utils.UUIdGenId;
import ${basePackage}.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import tk.mybatis.mapper.annotation.KeySql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* ${tableComment}
* @author ${AUTHOR}
* @date ${CREATE}
**/
@ApiModel(description = "${tableComment}")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="${tableName}")
public class ${entityName} implements Serializable{
    private static final long serialVersionUID = 1L;
<#list columns as column>

    ${(column.annotation)}
    private ${column.type} ${column.name};
</#list>

    @ApiModelProperty(value = "创建人名称")
    @Transient
    private String createUser;

    @ApiModelProperty(value = "更新人名称")
    @Transient
    private String updateUser;
}