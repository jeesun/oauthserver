
package ${basePackage}.serviceImpl;

import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.repository.${modelNameUpperCamel}Repository;
import ${basePackage}.common.service.BasicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ${AUTHOR}
* @create ${CREATE}
**/
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {
    @Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Autowired
    private ${modelNameUpperCamel}Repository ${modelNameLowerCamel}Repository;

    @Override
    public ${modelNameUpperCamel} save(${modelNameUpperCamel} ${modelNameLowerCamel}){
        return ${modelNameLowerCamel}Repository.save(${modelNameLowerCamel});
    }

    @Override
    public PageInfo<${modelNameUpperCamel}> findAll(int pageNo){
    PageHelper.startPage(pageNo, 10);
    List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Mapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public List<${modelNameUpperCamel}> findAll(){
        return ${modelNameLowerCamel}Repository.findAll();
    }

    @Override
    public void delete(Long id){
        ${modelNameLowerCamel}Repository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return ${modelNameLowerCamel}Mapper.deleteByIds(ids);
    }
}