package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.DictTypeDto;
import com.simon.dto.EasyUiTreeGridDto;
import com.simon.dto.SelectDto;
import com.simon.model.DictTypeGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictTypeGroupMapper extends MyMapper<DictTypeGroup> {
    List<DictTypeGroup> getAll(@Param("language") String language);

    List<EasyUiTreeGridDto> getTreeGridDtos(@Param("name") String name, @Param("code") String code, @Param("language") String language);

    List<DictTypeGroup> getList(@Param("map") Map<String, Object> map);

    /**
     * 根据id返回字典dto
     * @param id 字典组id
     * @return 字典dto
     */
    DictTypeDto getDtoById(@Param("id") Long id, @Param("language") String language);

    List<SelectDto> getSelectDtos(@Param("language") String language);
}