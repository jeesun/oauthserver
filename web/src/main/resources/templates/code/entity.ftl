package ${basePackage}.model;

import com.simon.common.domain.BasePo;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.liaochong.myexcel.core.WorkbookType;
import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import com.github.liaochong.myexcel.core.annotation.ExcelTable;
import com.github.liaochong.myexcel.core.annotation.ExcludeColumn;
import ${basePackage}.common.config.AppConfig;
import ${basePackage}.common.utils.UUIdGenId;
import ${basePackage}.common.utils.SnowflakeGenId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
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
@ExcelTable(sheetName = "${tableComment}", workbookType = WorkbookType.SXLSX, rowAccessWindowSize = 100, useFieldNameAsTitle = true)
@ApiModel(description = "${tableComment}")
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="${tableName}")
public class ${entityName} extends BasePo<${idType}> implements Serializable {
    @ExcludeColumn
    private static final long serialVersionUID = 1L;
<#list columns as column>

    ${(column.annotation)}
    private ${column.type} ${column.name};
</#list>

    @ExcludeColumn
    @ApiModelProperty(value = "创建人名称")
    @Transient
    private String createUser;

    @ExcludeColumn
    @ApiModelProperty(value = "更新人名称")
    @Transient
    private String updateUser;
}