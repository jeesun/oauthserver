
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.dto.FontAwesomeDto;
import com.simon.mapper.FontAwesomeMapper;
import com.simon.model.FontAwesome;
import com.simon.repository.FontAwesomeRepository;
import com.simon.service.FontAwesomeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-29
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class FontAwesomeServiceImpl implements FontAwesomeService {
    @Autowired
    private FontAwesomeMapper fontAwesomeMapper;

    @Autowired
    private FontAwesomeRepository fontAwesomeRepository;

    @Override
    public long count() {
        return fontAwesomeRepository.count();
    }

    @Override
    public FontAwesome save(FontAwesome fontAwesome){
        return fontAwesomeRepository.save(fontAwesome);
    }

    @Override
    public List<FontAwesome> save(List<FontAwesome> fontAwesomeList) {
        return fontAwesomeRepository.save(fontAwesomeList);
    }

    @Override
    public PageInfo<FontAwesome> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<FontAwesome> list = fontAwesomeMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<FontAwesome> findAll(Pageable pageable){
        return fontAwesomeRepository.findAll(pageable);
    }

    @Override
    public List<FontAwesome> findAll(){
        return fontAwesomeRepository.findAll();
    }

    @Override
    public void delete(Integer id){
        fontAwesomeRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return fontAwesomeMapper.deleteByIds(ids);
    }

    @Override
    public FontAwesome findById(Integer id){
        return fontAwesomeRepository.findOne(id);
    }

    @Override
    public int insertList(List<FontAwesome> list){
        return fontAwesomeMapper.insertList(list);
    }

    @Override
    public int insert(FontAwesome fontAwesome){
        return fontAwesomeMapper.insert(fontAwesome);
    }

    @Override
    public int insertSelective(FontAwesome fontAwesome){
        return fontAwesomeMapper.insertSelective(fontAwesome);
    }

    @Override
    public int updateByPrimaryKey(FontAwesome fontAwesome){
        return fontAwesomeMapper.updateByPrimaryKey(fontAwesome);
    }

    @Override
    public int updateByPrimaryKeySelective(FontAwesome fontAwesome){
        return fontAwesomeMapper.updateByPrimaryKeySelective(fontAwesome);
    }

    @Override
    public PageInfo<FontAwesome> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<FontAwesome> list = fontAwesomeMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public List<FontAwesomeDto> getDtos() {
        return fontAwesomeMapper.getDtos();
    }

    @Override
    public int countByIconClass(String iconClass) {
        return fontAwesomeMapper.countByIconClass(iconClass);
    }
}