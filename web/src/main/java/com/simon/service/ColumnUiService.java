package com.simon.service;

import com.simon.common.service.BasicService;
import com.simon.model.ColumnUi;

import java.util.List;

/**
* @author SimonSun
* @date 2019-05-03
**/
public interface ColumnUiService extends BasicService<ColumnUi, Long> {
    /**
     * 根据表名查询
     * @param tableName 表名
     * @return 属性UI列表
     */
    List<ColumnUi> findByTableName(String tableName);
}