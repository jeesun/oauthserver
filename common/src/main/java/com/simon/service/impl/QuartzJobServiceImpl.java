
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.plugins.quartz.QuartzManager;
import com.simon.mapper.QuartzJobMapper;
import com.simon.model.QuartzJob;
import com.simon.repository.QuartzJobRepository;
import com.simon.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author SimonSun
 * @date 2018-12-21
 **/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class QuartzJobServiceImpl implements QuartzJobService {
    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzJobRepository quartzJobRepository;

    private String TRIGGER_GROUP_NAME = "XLXXCC_JOB_GROUP";

    @Override
    public long count() {
        return quartzJobRepository.count();
    }

    @Override
    public QuartzJob save(QuartzJob quartzJob) {
        return quartzJobRepository.save(quartzJob);
    }

    @Override
    public List<QuartzJob> save(List<QuartzJob> quartzJobList) {
        return quartzJobRepository.save(quartzJobList);
    }

    @Override
    public PageInfo<QuartzJob> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize) {
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(pageNo, pageSize);
        } else {
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<QuartzJob> list = quartzJobMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<QuartzJob> findAll(Pageable pageable) {
        return quartzJobRepository.findAll(pageable);
    }

    @Override
    public List<QuartzJob> findAll() {
        return quartzJobRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        quartzJobRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids) {
        return quartzJobMapper.deleteByIds(ids);
    }

    @Override
    public QuartzJob findById(Long id) {
        return quartzJobRepository.findOne(id);
    }

    @Override
    public int insertList(List<QuartzJob> list) {
        return quartzJobMapper.insertList(list);
    }

    @Override
    public int insert(QuartzJob quartzJob) {
        return quartzJobMapper.insert(quartzJob);
    }

    @Override
    public int insertSelective(QuartzJob quartzJob) {
        return quartzJobMapper.insertSelective(quartzJob);
    }

    @Override
    public int updateByPrimaryKey(QuartzJob quartzJob) {
        return quartzJobMapper.updateByPrimaryKey(quartzJob);
    }

    @Override
    public int updateByPrimaryKeySelective(QuartzJob quartzJob) {
        return quartzJobMapper.updateByPrimaryKeySelective(quartzJob);
    }

    @Override
    public PageInfo<QuartzJob> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize) {
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(pageNo, pageSize);
        } else {
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        var list = quartzJobMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void batchSave(List<QuartzJob> list) {

    }

    @Override
    public void batchUpdate(List<QuartzJob> list) {

    }

    @Override
    public void runJobsOnStart() throws ClassNotFoundException, InstantiationException, SchedulerException, IllegalAccessException {
        List<QuartzJob> quartzJobs = quartzJobMapper.selectAll();
        for (QuartzJob quartzJob : quartzJobs) {
            QuartzManager.addJob(quartzJob, TRIGGER_GROUP_NAME);
        }
        quartzJobMapper.updateJobStatus(1);
    }
}