package ${basePackage}.repository;

import ${basePackage}.model.${modelNameUpperCamel};
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author ${AUTHOR}
* @date ${CREATE}
**/
@Repository
public interface ${modelNameUpperCamel}Repository extends JpaRepository<${modelNameUpperCamel}, ${idType}> {
}
