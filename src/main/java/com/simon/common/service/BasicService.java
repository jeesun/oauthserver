package com.simon.common.service;

import com.github.pagehelper.PageInfo;

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
    List<T> findAll();
    void delete(Long id);
    int deleteByIds(String ids);
}