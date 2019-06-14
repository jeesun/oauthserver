package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.FontAwesomeDto;
import com.simon.model.FontAwesome;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    List<FontAwesome> getList(@Param("labelEnUs") String labelEnUs, @Param("labelZhCn") String labelZhCn);

    List<FontAwesomeDto> getDtos(@Param("language") String language);

    int countByIconClass(@Param("iconClass") String iconClass);
}