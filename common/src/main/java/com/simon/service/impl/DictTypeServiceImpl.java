
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.dto.DictTypeDto;
import com.simon.mapper.DictTypeMapper;
import com.simon.mapper.DictTypeMultiLanguageMapper;
import com.simon.model.DictType;
import com.simon.model.DictTypeMultiLanguage;
import com.simon.repository.DictTypeGroupRepository;
import com.simon.repository.DictTypeMultiLanguageRepository;
import com.simon.repository.DictTypeRepository;
import com.simon.service.DictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author SimonSun
* @date 2018-09-06 10:03:50
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class DictTypeServiceImpl extends CrudServiceImpl<DictType, Long> implements DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictTypeRepository dictTypeRepository;

    @Autowired
    private DictTypeGroupRepository dictTypeGroupRepository;

    @Autowired
    private DictTypeMultiLanguageMapper dictTypeMultiLanguageMapper;

    @Autowired
    private DictTypeMultiLanguageRepository dictTypeMultiLanguageRepository;

    @Override
    public List<DictType> getTypeByGroupCode(String groupCode, String language) {
        return dictTypeMapper.getByGroupCode(groupCode, language);
    }

    @Override
    public DictType save(DictTypeDto dictTypeDto, String language) {
        DictType dictType = new DictType();
        if(StringUtils.isNotEmpty(dictTypeDto.getId())){
            dictType.setId(Long.parseLong(dictTypeDto.getId()));
        }
        dictType.setCreateDate(LocalDateTime.now());
        dictType.setTypeGroupId(Long.parseLong(dictTypeDto.getPid()));
        dictType.setTypeGroupCode(dictTypeGroupRepository.findOne(Long.parseLong(dictTypeDto.getPid())).getTypeGroupCode());
        dictType.setTypeName(dictTypeDto.getName());
        dictType.setTypeCode(dictTypeDto.getCode());
        dictType.setOrderNum(dictTypeDto.getOrderNum());
        dictType = dictTypeRepository.save(dictType);

        DictTypeMultiLanguage dictTypeMultiLanguage = dictTypeMultiLanguageRepository.findByDictTypeIdAndLanguage(dictType.getId(), language);
        if (null == dictTypeMultiLanguage) {
            dictTypeMultiLanguage = new DictTypeMultiLanguage();
        }
        dictTypeMultiLanguage.setDictTypeId(dictType.getId());
        dictTypeMultiLanguage.setLanguage(language);
        dictTypeMultiLanguage.setName(dictTypeDto.getName());
        dictTypeMultiLanguageRepository.save(dictTypeMultiLanguage);

        return dictType;
    }

    @Override
    public DictTypeDto getDtoById(Long id, String language) {
        return dictTypeMapper.getDtoById(id, language);
    }
}