package com.simon.repository;

import com.simon.model.ColumnUi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author SimonSun
* @date 2019-05-03
**/
@Repository
public interface ColumnUiRepository extends JpaRepository<ColumnUi, Integer> {
    /**
     * 根据表名删除
     * @param tableName 表名
     * @return 影响行数
     */
    int deleteByTableName(String tableName);

    /**
     * 根据表名查询
     * @param tableName 表名
     * @return 属性UI列表
     */
    List<ColumnUi> findByTableName(String tableName);
}
