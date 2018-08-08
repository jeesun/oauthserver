package ${entityPackage};

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
@Data
@Entity
@Table(name="${tableName}")
public class ${entityName} implements Serializable{
    private static final long serialVersionUID = 1L;
<#list columns as column>

    /**
    * ${(column.comment)}
    */
    ${(column.annotation)}
    private ${column.type} ${column.name};
</#list>
}