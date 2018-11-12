package com.simon.service;

import com.simon.common.service.BasicService;
import com.simon.dto.DictTypeDto;
import com.simon.model.DictTypeGroup;

import java.util.List;

/**
* @author SimonSun
* @date 2018-09-06 10:03:50
**/
public interface DictTypeGroupService extends BasicService<DictTypeGroup> {
    List<DictTypeDto> getDtos(Integer limit, Integer offset);
}