package ${entityPackage};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* @author ${AUTHOR}
* @create ${CREATE}
**/
@ApiModel(description = "${entityName}")
@Data
@Entity
@Table(name="${tableName}")
public class ${entityName} implements Serializable{
    private static final long serialVersionUID = 1L;
<#list columns as column>

    ${(column.annotation)}
    private ${column.type} ${column.name};
</#list>
}