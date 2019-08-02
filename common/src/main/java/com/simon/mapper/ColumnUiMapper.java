package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.model.ColumnUi;
import com.simon.provider.ColumnUiProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-05-03
**/
public interface ColumnUiMapper extends CrudMapper<ColumnUi> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @ResultMap("BaseResultMap")
    @SelectProvider(type = ColumnUiProvider.class, method = "getList")
    List<ColumnUi> getList(Map<String, Object> map);
}