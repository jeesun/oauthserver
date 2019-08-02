package com.simon.common.service;

import java.util.List;

/**
 * @author simon
 * @version 1.0
 * @date 2019-07-26 15:45
 */
public interface UpdateService<T, ID> {
    /**
     * 根据id更新记录（更新记录的全部属性）
     * @param model 待更新记录
     * @return 影响的记录条数
     */
    int updateByPrimaryKey(T model);

    /**
     * 根据id更新记录（只更新记录的非空属性）
     * @param model 待更新记录
     * @return 影响的记录条数
     */
    int updateByPrimaryKeySelective(T model);

    /**
     * 批量更新
     * @param list 要更新的数据
     */
    void batchUpdate(List<T> list);
}
