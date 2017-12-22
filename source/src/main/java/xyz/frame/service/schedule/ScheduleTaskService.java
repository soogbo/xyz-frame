package xyz.frame.service.schedule;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import xyz.frame.pojo.common.TaskStateEnum;
import xyz.frame.pojo.po.ScheduleTask;
import xyz.frame.pojo.vo.ScheduleTaskVo;

/**
 * 定时任务服务
 */
public interface ScheduleTaskService {
    
    /**
     * 获取所有任务(API)<br>
     * http方法：get<br>
     * @return 所有任务
     */
    public List<ScheduleTaskVo> getAllTask();

    /**
     * 添加或修改任务(API)<br>
     * http方法：post<br>
     * @param task 任务
     * @return ScheduleTask
     */
    public ScheduleTask saveOrUpdateTask(@NotNull(message="1|参数task不能为空")ScheduleTaskVo task);

    /**
     * 根据id获取任务(API)<br>
     * http方法：get<br>
     * @param taskId 任务id
     * @return 任务VO对象
     */
    public ScheduleTaskVo getOneTask(@NotNull(message="1|参数taskId不能为空")Long taskId);
    
    /**
     * 启动任务(API)<br>
     * http方法：post<br>
     * @param taskId 任务id
     * @return 任务状态
     */
    public TaskStateEnum startTask(@NotNull(message="1|参数taskId不能为空")Long taskId);

    /**
     * 暂停任务(API)<br>
     * http方法：post<br>
     * @param taskId 任务id
     * @return 任务状态
     */
    public TaskStateEnum pauseTask(@NotNull(message="1|参数taskId不能为空")Long taskId);

    /**
     * 重启任务(API)<br>
     * http方法：post<br>
     * @param taskId 任务id
     * @return 任务状态
     */
    public TaskStateEnum resumeTask(@NotNull(message="1|参数taskId不能为空")Long taskId);

    /**
     * 停止任务(API)<br>
     * http方法：post<br>
     * @param taskId 任务id
     * @return 任务状态
     */
    public TaskStateEnum stopTask(@NotNull(message="1|参数taskId不能为空")Long taskId);

    /**
     * 删除任务(API)<br>
     * http方法：post<br>
     * @param taskId 任务id
     * @return 任务状态
     */
    public TaskStateEnum deleteTask(@NotNull(message="1|参数taskId不能为空")Long taskId);

    /**
     * 执行一次任务(API)<br>
     * http方法：post<br>
     * @param taskId 任务id
     */
    public Boolean runOnceTask(@NotNull(message="1|参数taskId不能为空")Long taskId);
    
    /**
     * 执行service.executeJob*();要求参数service任务方法名必须是以 executeJob开头
     * 
     * @param executeJob
     * @param context
     * @param service
     */
    public void doExecuteJob(Job executeJob, JobExecutionContext context, Object service);

}
