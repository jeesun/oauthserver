package com.simon.common.service;

import java.util.List;

/**
 * @author simon
 * @version 1.0
 * @date 2019-07-26 15:45
 */
public interface InsertService<T, ID> {

    /**
     * 批量插入记录
     * @param list 记录列表
     * @return 影响的记录条数
     */
    int insertList(List<T> list);

    /**
     * 插入记录
     * @param model 记录
     * @return 影响的记录条数
     */
    int insert(T model);

    /**
     * 可选地插入记录（只保存记录的非空属性）
     * @param model 记录
     * @return 影响的记录条数
     */
    int insertSelective(T model);

    /**
     * 批量保存
     * @param list 要保存的数据
     */
    void batchSave(List<T> list);
}
