package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.DictTypeMultiLanguage;
import com.simon.provider.DictTypeMultiLanguageProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author jeesun
* @date 2019-06-03
**/
@Mapper
public interface DictTypeMultiLanguageMapper extends MyMapper<DictTypeMultiLanguage> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @ResultMap("BaseResultMap")
    @SelectProvider(type = DictTypeMultiLanguageProvider.class, method = "getList")
    List<DictTypeMultiLanguage> getList(Map<String, Object> map);
}