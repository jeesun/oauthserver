
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.dto.FontAwesomeDto;
import com.simon.mapper.FontAwesomeMapper;
import com.simon.model.FontAwesome;
import com.simon.repository.FontAwesomeRepository;
import com.simon.service.FontAwesomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author SimonSun
* @date 2019-04-29
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class FontAwesomeServiceImpl extends CrudServiceImpl<FontAwesome, Integer> implements FontAwesomeService {
    @Autowired
    private FontAwesomeMapper fontAwesomeMapper;

    @Autowired
    private FontAwesomeRepository fontAwesomeRepository;

    @Override
    public List<FontAwesomeDto> getDtos(String language) {
        return fontAwesomeMapper.getDtos(language);
    }

    @Override
    public int countByIconClass(String iconClass) {
        return fontAwesomeMapper.countByIconClass(iconClass);
    }
}