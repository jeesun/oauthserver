package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.EasyUiTreeGridDto;
import com.simon.model.DictTypeGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictTypeGroupMapper extends MyMapper<DictTypeGroup> {
    List<DictTypeGroup> getAll();

    List<EasyUiTreeGridDto> getTreeGridDtos(@Param("map") Map<String, Object> map);
}