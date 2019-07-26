package com.simon.common.service;

import java.util.List;

/**
 * Basic Service
 *
 * @author simon
 * @date 2018-08-02
 **/

public interface BasicService<T, ID> extends InsertService<T, ID>, UpdateService<T, ID>, DeleteService<T, ID>, SelectService<T, ID> {

    /**
     * 新建或更新记录
     * @param model 记录
     * @return 批量新建或更新后的记录
     */
    @Deprecated
    T save(T model);

    /**
     * 批量新建或更新记录
     * @param modelList 记录列表
     * @return 批量新建或更新后的记录列表
     */
    @Deprecated
    List<T> save(List<T> modelList);

}