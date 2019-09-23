package com.simon.client;

import com.simon.common.domain.ResultCode;
import com.simon.common.domain.ResultMsg;
import com.simon.model.QuartzJob;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-23 10:32
 */
@Component
public class TaskClientFallBack implements TaskClient {
    @Override
    public ResultMsg<List<QuartzJob>> getAllJobs() {
        return ResultMsg.fail(ResultCode.FAIL);
    }
}
