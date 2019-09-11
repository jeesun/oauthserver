
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.model.QuartzJob;
import com.simon.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author SimonSun
 * @date 2018-12-21
 **/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class QuartzJobServiceImpl extends CrudServiceImpl<QuartzJob, Long> implements QuartzJobService {
}