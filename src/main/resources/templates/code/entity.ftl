package ${entityPackage};

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
* @author ${AUTHOR}
* @create ${CREATE}
**/
@ApiModel(value = "${tableComment}")
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