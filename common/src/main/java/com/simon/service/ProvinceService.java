package com.simon.service;

import com.simon.common.service.CrudService;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.model.Province;

import java.util.List;

/**
* @author SimonSun
* @date 2019-04-24
**/
public interface ProvinceService extends CrudService<Province, Integer> {
    /**
     * Element UI Cascader级联选择器数据
     * @return Cascader级联选择器数据
     */
    List<CascaderOptionDto> getCascaderOptionDtos();
}