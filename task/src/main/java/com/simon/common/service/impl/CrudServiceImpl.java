package com.simon.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.domain.PO;
import com.simon.common.mapper.CrudMapper;
import com.simon.common.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author simon
 * @version 1.0
 * @date 2019-07-27 14:48
 */
@Slf4j
public abstract class CrudServiceImpl<T extends PO<ID>, ID extends java.io.Serializable> implements CrudService<T, ID> {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    protected CrudMapper<T> crudMapper;

    @Autowired
    protected JpaRepository<T, ID> jpaRepository;

    protected Class<T> poType;

    @SuppressWarnings("unchecked")
    public CrudServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        poType = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public T save(T model) {
        return jpaRepository.save(model);
    }

    @Override
    public List<T> save(List<T> list) {
        return jpaRepository.saveAll(list);
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    @Override
    public void delete(ID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public int deleteByIds(String ids) {
        return crudMapper.deleteByIds(ids);
    }

    @Override
    public T findById(ID id) {
        return crudMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(T model) {
        return crudMapper.insert(model);
    }

    @Override
    public int insertSelective(T model) {
        return crudMapper.insertSelective(model);
    }

    @Override
    public int insertList(List<T> list) {
        return crudMapper.insertList(list);
    }

    @Override
    public int updateByPrimaryKey(T model) {
        return crudMapper.updateByPrimaryKey(model);
    }

    @Override
    public int updateByPrimaryKeySelective(T model) {
        return crudMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public void batchSave(List<T> list) {

    }

    @Override
    public void batchUpdate(List<T> list) {

    }

    @Override
    public PageInfo<T> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize) {
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(pageNo, pageSize);
        } else {
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<T> list = crudMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<T> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize) {
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(pageNo, pageSize);
        } else {
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<T> list = crudMapper.getList(params);
        return new PageInfo<>(list);
    }

}
