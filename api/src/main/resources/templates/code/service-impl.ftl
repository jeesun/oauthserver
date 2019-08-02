
package ${basePackage}.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.repository.${modelNameUpperCamel}Repository;
import ${basePackage}.common.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* @author ${AUTHOR}
* @date ${CREATE}
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class ${modelNameUpperCamel}ServiceImpl extends CrudServiceImpl<${modelNameUpperCamel}, ${idType}> implements ${modelNameUpperCamel}Service {

}