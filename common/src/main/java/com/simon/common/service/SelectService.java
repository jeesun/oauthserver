package com.simon.common.service;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author simon
 * @version 1.0
 * @date 2019-07-26 15:46
 */
public interface SelectService<T, ID> {
    /**
     * 统计记录总条数
     * @return 记录总条数
     */
    long count();

    /**
     * PageHelper分页查询
     * @param pageNo 页码
     * @param pageSize 每页记录数
     * @param orderBy 排序
     * @return 分页数据
     */
    PageInfo<T> findAll(Integer pageNo, Integer pageSize, String orderBy);

    /**
     * JPA分页查询
     * @param pageable 分页对象
     * @return 分页数据
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 查询所有记录
     * @return 所有记录
     */
    List<T> findAll();

    /**
     * 根据id查询记录
     * @param id 记录id
     * @return 记录
     */
    T findById(ID id);

    /**
     * 通用查询
     * @param params 参数
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param orderBy 排序
     * @return 分页数据
     */
    PageInfo<T> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy);

}
