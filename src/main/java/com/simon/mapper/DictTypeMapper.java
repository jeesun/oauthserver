package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.DictType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author simon
 */
public interface DictTypeMapper extends MyMapper<DictType> {
    /**
     * 根据字典组编码查询字典列表
     * @param groupCode
     * @return
     */
    List<DictType> getByGroupCode(@Param("groupCode") String groupCode);
}