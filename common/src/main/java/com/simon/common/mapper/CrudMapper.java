package com.simon.common.mapper;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;
import java.util.Map;

/**
 * 通用mapper
 *
 * @author simon
 * @create 2018-07-23 0:37
 **/

public interface CrudMapper<T> extends BaseMapper<T>,ConditionMapper<T>,IdsMapper<T>,InsertListMapper<T> {
    List<T> getList(Map<String, Object> map);
}
