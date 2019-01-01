
package com.simon.service.impl;

import com.simon.mapper.NoticeMsgMapper;
import com.simon.model.NoticeMsg;
import com.simon.service.NoticeMsgService;
import com.simon.repository.NoticeMsgRepository;
import com.simon.common.config.AppConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-11-24
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class NoticeMsgServiceImpl implements NoticeMsgService {
    @Autowired
    private NoticeMsgMapper noticeMsgMapper;

    @Autowired
    private NoticeMsgRepository noticeMsgRepository;

    @Override
    public long count() {
        return noticeMsgRepository.count();
    }

    @Override
    public NoticeMsg save(NoticeMsg noticeMsg){
        return noticeMsgRepository.save(noticeMsg);
    }

    @Override
    public List<NoticeMsg> save(List<NoticeMsg> noticeMsgList) {
        return noticeMsgRepository.save(noticeMsgList);
    }

    @Override
    public PageInfo<NoticeMsg> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<NoticeMsg> list = noticeMsgMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<NoticeMsg> findAll(Pageable pageable){
        return noticeMsgRepository.findAll(pageable);
    }

    @Override
    public List<NoticeMsg> findAll(){
        return noticeMsgRepository.findAll();
    }

    @Override
    public void delete(Long id){
        noticeMsgRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return noticeMsgMapper.deleteByIds(ids);
    }

    @Override
    public NoticeMsg findById(Long id){
        return noticeMsgRepository.findOne(id);
    }

    @Override
    public int insertList(List<NoticeMsg> list){
        return noticeMsgMapper.insertList(list);
    }

    @Override
    public int insert(NoticeMsg noticeMsg){
        return noticeMsgMapper.insert(noticeMsg);
    }

    @Override
    public int insertSelective(NoticeMsg noticeMsg){
        return noticeMsgMapper.insertSelective(noticeMsg);
    }

    @Override
    public int updateByPrimaryKey(NoticeMsg noticeMsg){
        return noticeMsgMapper.updateByPrimaryKey(noticeMsg);
    }

    @Override
    public int updateByPrimaryKeySelective(NoticeMsg noticeMsg){
        return noticeMsgMapper.updateByPrimaryKeySelective(noticeMsg);
    }

    @Override
    public PageInfo<NoticeMsg> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        return null;
    }
}