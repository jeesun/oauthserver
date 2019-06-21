
package ${basePackage}.service.impl;

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
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Autowired
    private ${modelNameUpperCamel}Repository ${modelNameLowerCamel}Repository;

    @Override
    public long count() {
        return ${modelNameLowerCamel}Repository.count();
    }

    @Override
    public ${modelNameUpperCamel} save(${modelNameUpperCamel} ${modelNameLowerCamel}){
        return ${modelNameLowerCamel}Repository.save(${modelNameLowerCamel});
    }

    @Override
    public List<${modelNameUpperCamel}> save(List<${modelNameUpperCamel}> ${modelNameLowerCamel}List) {
        return ${modelNameLowerCamel}Repository.save(${modelNameLowerCamel}List);
    }

    @Override
    public PageInfo<${modelNameUpperCamel}> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
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
    public void delete(${idType} id){
        ${modelNameLowerCamel}Repository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return ${modelNameLowerCamel}Mapper.deleteByIds(ids);
    }

    @Override
    public ${modelNameUpperCamel} findById(${idType} id){
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
    public int updateByPrimaryKeySelective(${modelNameUpperCamel} ${modelNameLowerCamel}){
        return ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(${modelNameLowerCamel});
    }

    @Override
    public PageInfo<${modelNameUpperCamel}> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Mapper.getList(params);
        return new PageInfo<>(list);
    }
}