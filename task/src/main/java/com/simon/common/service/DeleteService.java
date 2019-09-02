package com.simon.common.service;

/**
 * @author simon
 * @version 1.0
 * @date 2019-07-26 15:46
 */
public interface DeleteService<T, ID> {

    /**
     * 根据id删除记录
     * @param id 记录id
     */
    void delete(ID id);

    /**
     * 根据id批量删除记录
     * @param ids 批量记录id，逗号隔开
     * @return 影响的记录条数
     */
    int deleteByIds(String ids);

}
