package com.simon.common.service;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Basic Service
 *
 * @author simon
 * @date 2018-08-02
 **/

public interface BasicService<T> {
    /**
     * 统计记录总条数
     * @return 记录总条数
     */
    long count();

    /**
     * 保存记录
     * @param model 记录
     * @return 保存后的记录
     */
    T save(T model);

    /**
     * 批量保存记录
     * @param modelList 记录列表
     * @return 保存后的记录列表
     */
    List<T> save(List<T> modelList);

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
     * 根据id删除记录
     * @param id 记录id
     */
    void delete(Long id);

    /**
     * 根据id批量删除记录
     * @param ids 批量记录id，逗号隔开
     * @return 影响的记录条数
     */
    int deleteByIds(String ids);

    /**
     * 根据id查询记录
     * @param id 记录id
     * @return 记录
     */
    T findById(Long id);

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
}