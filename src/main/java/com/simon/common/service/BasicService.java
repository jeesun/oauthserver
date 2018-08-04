package com.simon.common.service;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Basic Service
 *
 * @author simon
 * @create 2018-08-02 11:06
 **/

public interface BasicService<T> {
    T save(T model);
    PageInfo<T> findAll(int pageNo);
    Page<T> findAll(Pageable pageable);
    List<T> findAll();
    void delete(Long id);
    int deleteByIds(String ids);
    T findById(Long id);
    int insertList(List<T> list);
    int insert(T model);
    int insertSelective(T model);
}