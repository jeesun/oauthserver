package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.model.QuartzJob;
import com.simon.service.DictTypeService;
import com.simon.service.QuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Locale;

/**
 * quartz任务
 *
 * @author SimonSun
 * @date 2018-12-22
 **/
@Slf4j
@ApiIgnore
@Api(value = "quartz任务")
@Controller
@RequestMapping("/api/quartzJobs")
public class QuartzJobController extends BaseController {

    @Autowired
    private QuartzJobService quartzJobService;

    @Autowired
    private DictTypeService dictTypeService;

    private String TRIGGER_GROUP_NAME = "XLXXCC_JOB_GROUP";

    @ApiOperation(value = "列表页面")
    @GetMapping("list")
    public String list(Model model, Locale locale) {
        model.addAttribute("jobStatusList", listToMap(dictTypeService.getTypeByGroupCode("job_status", locale.toString())));
        return "vue/quartzJob/list";
    }

    @ApiOperation(value = "新增页面")
    @GetMapping("add")
    public String add(Model model) {
        return "vue/quartzJob/add";
    }

    @ApiOperation(value = "编辑页面")
    @GetMapping("edit")
    public String edit(@RequestParam String jobName, @RequestParam String jobGroup, @RequestParam String cronExpression, @RequestParam String description, Model model) {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setJobName(jobName);
        quartzJob.setJobGroup(jobGroup);
        quartzJob.setCronExpression(cronExpression);
        quartzJob.setDescription(description);
        model.addAttribute("entity", quartzJob);
        return "vue/quartzJob/edit";
    }

    @ApiOperation(value = "列表数据")
    @GetMapping("data")
    @ResponseBody
    public EasyUIDataGridResult<QuartzJob> getEasyUIList(
            @ApiParam(value = "类名") @RequestParam(required = false) String beanName,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true) @RequestParam Integer pageSize,
            @ApiParam(value = "排序") @RequestParam(required = false, defaultValue = "") String orderBy) {

        return new EasyUIDataGridResult<>(quartzJobService.getAllJobs().getData());
    }

    @ApiOperation(value = "新增")
    @PostMapping("add")
    @ResponseBody
    public ResultMsg add(@RequestBody QuartzJob body, Authentication authentication) {
        quartzJobService.addJob(body);
        return ResultMsg.success();
    }

    @ApiOperation(value = "修改", notes = "限制只能修改cron表达式和任务描述")
    @PatchMapping("edit")
    @ResponseBody
    public ResultMsg update(@RequestBody QuartzJob body, Authentication authentication) throws ClassNotFoundException, InstantiationException, SchedulerException, IllegalAccessException {
        quartzJobService.edit(body);
        return ResultMsg.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping
    @ResponseBody
    public ResultMsg delete(@RequestParam String jobName, @RequestParam String jobGroup) {
        quartzJobService.delete(jobName, jobGroup);
        return ResultMsg.success();
    }

    @ApiOperation(value = "定时任务操作（启动，暂停）")
    @PostMapping("/updateJobStatus")
    @ResponseBody
    public ResultMsg operation(@RequestBody QuartzJob body, Authentication authentication) throws ClassNotFoundException, InstantiationException, SchedulerException, IllegalAccessException {
        if (0 == body.getJobStatus()) {
            quartzJobService.pauseJob(body.getJobName(), body.getJobGroup());
        } else {
            quartzJobService.resume(body.getJobName(), body.getJobGroup());
        }
        return ResultMsg.success();
    }
}