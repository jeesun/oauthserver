package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.model.QuartzJob;
import com.simon.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-11 15:47
 */
@Slf4j
@Api(tags = {"Quartz定时任务集群"})
@RestController
@RequestMapping("/api/tasks")
public class TaskController extends BaseController {
    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "获取所有任务")
    @GetMapping
    public ResultMsg getAllJobs() {
        return ResultMsg.success(taskService.list());
    }

    @ApiOperation(value = "添加定时任务")
    @PostMapping
    public ResultMsg add(@RequestBody QuartzJob quartzJob) {
        taskService.addJob(quartzJob);
        return ResultMsg.success();
    }

    @ApiOperation(value = "修改定时任务")
    @PatchMapping
    public ResultMsg edit(@RequestBody QuartzJob quartzJob) {
        taskService.edit(quartzJob);
        return ResultMsg.success();
    }

    @ApiOperation(value = "删除定时任务")
    @DeleteMapping
    public ResultMsg delete(
            @ApiParam(value = "任务名称", required = true) @RequestParam String jobName,
            @ApiParam(value = "任务组", required = true) @RequestParam String jobGroup) {
        taskService.delete(jobName, jobGroup);
        return ResultMsg.success();
    }

    @ApiOperation(value = "暂停定时任务")
    @PatchMapping("/pause")
    public ResultMsg pause(
            @ApiParam(value = "任务名称", required = true) @RequestParam String jobName,
            @ApiParam(value = "任务组", required = true) @RequestParam String jobGroup) {
        taskService.pause(jobName, jobGroup);
        return ResultMsg.success();
    }

    @ApiOperation(value = "恢复定时任务")
    @PatchMapping("/resume")
    public ResultMsg resume(
            @ApiParam(value = "任务名称", required = true) @RequestParam String jobName,
            @ApiParam(value = "任务组", required = true) @RequestParam String jobGroup) {
        taskService.resume(jobName, jobGroup);
        return ResultMsg.success();
    }

    @ApiOperation(value = "判断定时任务是否存在")
    @GetMapping("checkExists")
    public ResultMsg checkExists(
            @ApiParam(value = "任务名称", required = true) @RequestParam String jobName,
            @ApiParam(value = "任务组", required = true) @RequestParam String jobGroup) throws SchedulerException {
        return ResultMsg.success(taskService.checkExists(jobName, jobGroup));
    }
}