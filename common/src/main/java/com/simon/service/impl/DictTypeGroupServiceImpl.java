
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.dto.DictTypeDto;
import com.simon.dto.EasyUiTreeGridDto;
import com.simon.dto.SelectDto;
import com.simon.mapper.DictTypeGroupMapper;
import com.simon.mapper.DictTypeGroupMultiLanguageMapper;
import com.simon.model.DictType;
import com.simon.model.DictTypeGroup;
import com.simon.model.DictTypeGroupMultiLanguage;
import com.simon.repository.DictTypeGroupMultiLanguageRepository;
import com.simon.repository.DictTypeGroupRepository;
import com.simon.service.DictTypeGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SimonSun
 * @date 2018-09-06 10:03:50
 **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class DictTypeGroupServiceImpl extends CrudServiceImpl<DictTypeGroup, Long> implements DictTypeGroupService {
    @Autowired
    private DictTypeGroupMapper dictTypeGroupMapper;

    @Autowired
    private DictTypeGroupRepository dictTypeGroupRepository;

    @Autowired
    private DictTypeGroupMultiLanguageMapper dictTypeGroupMultiLanguageMapper;

    @Autowired
    private DictTypeGroupMultiLanguageRepository dictTypeGroupMultiLanguageRepository;

    @Override
    public void delete(Long id) {
        super.delete(id);
        dictTypeGroupMultiLanguageRepository.deleteByDictTypeGroupId(id);
    }

    @Override
    public List<DictTypeDto> getDtos(String language, Integer limit, Integer offset) {
        List<DictTypeDto> dtoList = new ArrayList<>();
        /*List<DictTypeGroup> groups = dictTypeGroupRepository.findAll(new PageRequest((offset/limit - 1), limit, Sort.Direction.DESC, "id")).getContent();*/
        PageHelper.startPage(offset / limit + 1, limit);
        List<DictTypeGroup> list = dictTypeGroupMapper.getAll(language);
        PageInfo<DictTypeGroup> pageInfo = new PageInfo<>(list);
        List<DictTypeGroup> resultList = pageInfo.getList();
        for (int i = 0; i < resultList.size(); i++) {
            DictTypeGroup dictTypeGroup = resultList.get(i);
            DictTypeDto dto = new DictTypeDto();
            dto.setId(String.valueOf(dictTypeGroup.getId()));
            dto.setName(dictTypeGroup.getTypeGroupName());
            dto.setCode(dictTypeGroup.getTypeGroupCode());
            //一级菜单
            dto.setType(1);
            dtoList.add(dto);

            List<DictType> dictTypes = dictTypeGroup.getDictTypes();
            if (null != dictTypes && dictTypes.size() > 0) {
                for (int j = 0; j < dictTypes.size(); j++) {
                    DictType dictType = dictTypes.get(j);
                    DictTypeDto subDto = new DictTypeDto();
                    subDto.setId(dictTypeGroup.getId() + "-" + dictType.getId());
                    subDto.setName(dictType.getTypeName());
                    subDto.setCode(dictType.getTypeCode());
                    subDto.setOrderNum(dictType.getOrderNum());
                    subDto.setPid(String.valueOf(dictTypeGroup.getId()));
                    //二级菜单
                    subDto.setType(2);

                    dtoList.add(subDto);
                }
            }
        }
        return dtoList;
    }

    @Override
    public PageInfo<EasyUiTreeGridDto> getTreeGridDtos(String name, String code, String language, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize) {
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(pageNo, pageSize);
        } else {
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }

        List<EasyUiTreeGridDto> list = dictTypeGroupMapper.getTreeGridDtos(name, code, language);
        return new PageInfo<>(list);
    }

    @Override
    public DictTypeGroup save(DictTypeDto dictTypeDto, String language) {
        DictTypeGroup dictTypeGroup = new DictTypeGroup();
        if (StringUtils.isNotEmpty(dictTypeDto.getId())) {
            dictTypeGroup.setId(Long.parseLong(dictTypeDto.getId()));
        }
        dictTypeGroup.setCreateDate(LocalDateTime.now());
        dictTypeGroup.setTypeGroupName(dictTypeDto.getName());
        dictTypeGroup.setTypeGroupCode(dictTypeDto.getCode());
        dictTypeGroup = dictTypeGroupRepository.save(dictTypeGroup);

        DictTypeGroupMultiLanguage dictTypeGroupMultiLanguage = dictTypeGroupMultiLanguageRepository.findByDictTypeGroupIdAndLanguage(dictTypeGroup.getId(), language);
        if (null == dictTypeGroupMultiLanguage) {
            dictTypeGroupMultiLanguage = new DictTypeGroupMultiLanguage();
        }
        dictTypeGroupMultiLanguage.setLanguage(language);
        dictTypeGroupMultiLanguage.setDictTypeGroupId(dictTypeGroup.getId());
        dictTypeGroupMultiLanguage.setName(dictTypeDto.getName());
        dictTypeGroupMultiLanguageRepository.save(dictTypeGroupMultiLanguage);

        return dictTypeGroup;
    }

    @Override
    public int countByTypeGroupCode(String typeGroupCode) {
        return dictTypeGroupRepository.countByTypeGroupCode(typeGroupCode);
    }

    @Override
    public DictTypeDto getDtoById(Long id, String language) {
        return dictTypeGroupMapper.getDtoById(id, language);
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<SelectDto> getSelectDtos(String language) {
        return dictTypeGroupMapper.getSelectDtos(language);
    }
}