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
}