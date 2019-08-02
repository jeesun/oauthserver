
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.ColumnUiMapper;
import com.simon.model.ColumnUi;
import com.simon.repository.ColumnUiRepository;
import com.simon.service.ColumnUiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author SimonSun
* @date 2019-05-03
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class ColumnUiServiceImpl extends CrudServiceImpl<ColumnUi, Long> implements ColumnUiService {
    @Autowired
    private ColumnUiMapper columnUiMapper;

    @Autowired
    private ColumnUiRepository columnUiRepository;

    @Override
    public List<ColumnUi> findByTableName(String tableName) {
        return columnUiRepository.findByTableName(tableName);
    }
}