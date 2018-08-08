
package ${basePackage}.serviceImpl;

import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.repository.${modelNameUpperCamel}Repository;
import ${basePackage}.common.config.AppConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<${modelNameUpperCamel}> save(List<${modelNameUpperCamel}> ${modelNameLowerCamel}List) {
        return ${modelNameLowerCamel}Repository.save(${modelNameLowerCamel}List);
    }

    @Override
    public PageInfo<${modelNameUpperCamel}> findAll(int pageNo){
        PageHelper.startPage(pageNo, AppConfig.DEFAULT_PAGE_SIZE);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Mapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<${modelNameUpperCamel}> findAll(Pageable pageable){
        return ${modelNameLowerCamel}Repository.findAll(pageable);
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

    @Override
    public ${modelNameUpperCamel} findById(Long id){
        return ${modelNameLowerCamel}Repository.findOne(id);
    }

    @Override
    public int insertList(List<${modelNameUpperCamel}> list){
        return ${modelNameLowerCamel}Mapper.insertList(list);
    }

    @Override
    public int insert(${modelNameUpperCamel} ${modelNameLowerCamel}){
        return ${modelNameLowerCamel}Mapper.insert(${modelNameLowerCamel});
    }

    @Override
    public int insertSelective(${modelNameUpperCamel} ${modelNameLowerCamel}){
        return ${modelNameLowerCamel}Mapper.insertSelective(${modelNameLowerCamel});
    }

    @Override
    public int updateByPrimaryKey(${modelNameUpperCamel} ${modelNameLowerCamel}){
        return ${modelNameLowerCamel}Mapper.updateByPrimaryKey(${modelNameLowerCamel});
    }

    @Override
    public int updateByPrimaryKeySelective(){
        return ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(${modelNameLowerCamel});
    }
}