/**
 * 
 */
package xyz.frame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.pojo.common.TaskStateEnum;
import xyz.frame.pojo.po.ScheduleTask;
import xyz.frame.pojo.vo.ScheduleTaskVo;
import xyz.frame.service.schedule.ScheduleTaskService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description 测试定时任务执行
 * @author shisp
 * @date 2017年12月14日  下午1:14:37
 */

@RestController
@RequestMapping("/schedule")
public class ScheduleTaskController {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @PostMapping(value = "/startTask/{taskId}")
    public ResponseResult<?> startTask(@PathVariable Long taskId) {
        TaskStateEnum startTask = scheduleTaskService.startTask(taskId);
        return RestResultUtil.success(startTask);
    }
    @PostMapping(value = "/runOnceTask/{taskId}")
    public ResponseResult<?> runOnceTask(@PathVariable Long taskId) {
        Boolean runOnceTask = scheduleTaskService.runOnceTask(taskId);
        return RestResultUtil.success(runOnceTask);
    }
    @PostMapping(value = "/pauseTask/{taskId}")
    public ResponseResult<?> pauseTask(@PathVariable Long taskId) {
        TaskStateEnum pauseTask = scheduleTaskService.pauseTask(taskId);
        return RestResultUtil.success(pauseTask);
    }
    @PostMapping(value = "/stopTask/{taskId}")
    public ResponseResult<?> stopTask(@PathVariable Long taskId) {
        TaskStateEnum stopTask = scheduleTaskService.stopTask(taskId);
        return RestResultUtil.success(stopTask);
    }
    @PostMapping(value = "/resumeTask/{taskId}")
    public ResponseResult<?> resumeTask(@PathVariable Long taskId) {
        TaskStateEnum resumeTask = scheduleTaskService.resumeTask(taskId);
        return RestResultUtil.success(resumeTask);
    }
    @PostMapping(value = "/saveOrUpdateTask")
    public ResponseResult<?> saveOrUpdateTask(ScheduleTaskVo task) {
        ScheduleTask saveOrUpdateTask = scheduleTaskService.saveOrUpdateTask(task);
        return RestResultUtil.success(saveOrUpdateTask);
    }
    @PostMapping(value = "/deleteTask/{taskId}")
    public ResponseResult<?> deleteTask(@PathVariable Long taskId) {
        TaskStateEnum deleteTask = scheduleTaskService.deleteTask(taskId);
        return RestResultUtil.success(deleteTask);
    }
    
    @GetMapping(value = "/getOneTask")
    public ResponseResult<?> getOneTask(Long taskId) {
        ScheduleTaskVo oneTask = scheduleTaskService.getOneTask(taskId);
        return RestResultUtil.success(oneTask);
    }
    @GetMapping(value = "/getAllTask")
    public ResponseResult<?> getAllTask() {
        List<ScheduleTaskVo> allTask = scheduleTaskService.getAllTask();
        return RestResultUtil.success(allTask);
    }
}
