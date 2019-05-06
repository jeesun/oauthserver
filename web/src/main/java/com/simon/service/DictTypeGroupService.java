package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.BasicService;
import com.simon.dto.DictTypeDto;
import com.simon.dto.EasyUiTreeGridDto;
import com.simon.model.DictTypeGroup;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-09-06 10:03:50
**/
public interface DictTypeGroupService extends BasicService<DictTypeGroup, Long> {
    List<DictTypeDto> getDtos(Integer limit, Integer offset);

    PageInfo<EasyUiTreeGridDto> getTreeGridDtos(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy);

    DictTypeGroup save(DictTypeDto dictTypeDto);

    int countByTypeGroupCode(String typeGroupCode);

    /**
     * 根据id返回字典dto
     * @param id 字典组id
     * @return 字典dto
     */
    DictTypeDto getDtoById(Long id);

    /**
     * 根据id删除记录
     * @param id
     * @return
     */
    int deleteById(Long id);
}