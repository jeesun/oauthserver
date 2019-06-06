package ${basePackage}.repository;

import ${basePackage}.model.${modelNameUpperCamel};
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* @author ${AUTHOR}
* @date ${CREATE}
**/
@Repository
public interface ${modelNameUpperCamel}Repository extends JpaRepository<${modelNameUpperCamel}, ${idType}> {
}
