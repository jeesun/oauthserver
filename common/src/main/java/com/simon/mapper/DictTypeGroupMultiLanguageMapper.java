package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.DictTypeGroupMultiLanguage;
import com.simon.provider.DictTypeGroupMultiLanguageProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* @author jeesun
* @date 2019-06-03
**/
@Mapper
public interface DictTypeGroupMultiLanguageMapper extends MyMapper<DictTypeGroupMultiLanguage> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @ResultMap("BaseResultMap")
    @SelectProvider(type = DictTypeGroupMultiLanguageProvider.class, method = "getList")
    List<DictTypeGroupMultiLanguage> getList(Map<String, Object> map);
}