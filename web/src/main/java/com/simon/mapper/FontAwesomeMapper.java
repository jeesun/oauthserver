package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.FontAwesomeDto;
import com.simon.model.FontAwesome;
import com.simon.provider.FontAwesomeProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-29
**/
public interface FontAwesomeMapper extends MyMapper<FontAwesome> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @ResultMap("BaseResultMap")
    @SelectProvider(type = FontAwesomeProvider.class, method = "getList")
    List<FontAwesome> getList(Map<String, Object> map);

    List<FontAwesomeDto> getDtos();

    int countByIconClass(@Param("iconClass") String iconClass);
}