package ${basePackage}.repository;

import ${basePackage}.model.${modelNameUpperCamel};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${modelNameUpperCamel}Repository extends JpaRepository<${modelNameUpperCamel}, Long> {
}
