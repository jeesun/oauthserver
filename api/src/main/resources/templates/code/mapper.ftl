package ${basePackage}.mapper;

import ${basePackage}.common.mapper.MyMapper;
import ${basePackage}.model.${modelNameUpperCamel};
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* ${modelNameUpperCamel}
* @author ${AUTHOR}
* @date ${CREATE}
**/
public interface ${modelNameUpperCamel}Mapper extends MyMapper<${modelNameUpperCamel}> {
    /**
     * 使用Map查询
     * @param map map查询条件
     * @return 查询结果
     */
    List<${modelNameUpperCamel}> getList(@Param("map") Map<String, Object> map);
}