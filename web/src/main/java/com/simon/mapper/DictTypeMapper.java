package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.EasyUiTreeGridDto;
import com.simon.model.DictType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<EasyUiTreeGridDto> getTreeGridDtos(@Param("groupCode") String groupCode);

    List<DictType> getList(@Param("map") Map<String, Object> map);
}