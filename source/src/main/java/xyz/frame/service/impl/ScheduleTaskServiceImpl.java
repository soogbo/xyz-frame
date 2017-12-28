package xyz.frame.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.configure.schedule.QuartzTaskHelper;
import xyz.frame.exception.ServiceException;
import xyz.frame.mapper.ScheduleTaskMapper;
import xyz.frame.pojo.common.TaskStateEnum;
import xyz.frame.pojo.po.ScheduleTask;
import xyz.frame.pojo.vo.ScheduleTaskVo;
import xyz.frame.service.schedule.ScheduleTaskService;

@Service("scheduleTaskService")
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTaskServiceImpl.class);
    private static final String EXECUTE_JOB = "executeJob";
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ScheduleTaskMapper scheduleTaskMapper;

    @Autowired
    private QuartzTaskHelper quartzTaskHelper;
    
    @Override
    public List<ScheduleTaskVo> getAllTask() {
        logger.info("begin getAllTask...");
        List<ScheduleTask> tasks = scheduleTaskMapper.findScheduleTaskList();
        if (tasks != null) {
            List<ScheduleTaskVo> list = new ArrayList<ScheduleTaskVo>();
            for (ScheduleTask task : tasks) {
                ScheduleTaskVo scheduleTaskVo = this.convertScheduleTask(task);
                list.add(scheduleTaskVo);
            }
            logger.info("allTasks:"+ tasks);
            return list;
        }
        return null;
    }

    @Override
    public ScheduleTask saveOrUpdateTask(ScheduleTaskVo task) {
        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.setName(task.getTaskName());
        scheduleTask.setTaskGroup(task.getTaskGroup());
        scheduleTask.setRepeatInterval(task.getRepeatInterval());
        scheduleTask.setDescription(task.getDesc());
        scheduleTask.setCronExpression(task.getCronExpression());
        scheduleTask.setJobClass(task.getJobClass());
        scheduleTask.setType(task.getType());
        scheduleTask.setStatus(TaskStateEnum.NONE.name());
        ScheduleTask isSelect = scheduleTaskMapper.selectByPrimaryKey(task.getTaskId());
        if (null == isSelect) {
            scheduleTask.setCreateAt(new Date());
            logger.info("save task:" + scheduleTask);
            scheduleTaskMapper.insert(scheduleTask);
        } else {
            scheduleTask.setId(task.getTaskId());
            scheduleTask.setStatus(task.getStatus());
            scheduleTask.setUpdateAt(new Date());
            Date nowTime = new Date();
            scheduleTask.setPreRunningTime(nowTime);
            Date nextFireTime = quartzTaskHelper.getNextFireTime(task.getTaskName());
            scheduleTask.setNextRunningTime(nextFireTime);;
            logger.info("update task:" + scheduleTask);
            scheduleTaskMapper.updateByPrimaryKeySelective(scheduleTask);
        }
        return scheduleTask;
    }

    @Override
    public ScheduleTaskVo getOneTask(Long taskId) {
        logger.info("getOneTask:taskId" + taskId);
        ScheduleTask scheduleTask = scheduleTaskMapper.selectByPrimaryKey(taskId);
        return this.convertScheduleTask(scheduleTask);
    }
    
    /**
     * 实体类转换ScheduleTask->ScheduleTaskVo
     */
    private ScheduleTaskVo convertScheduleTask(ScheduleTask task) {
        ScheduleTaskVo scheduleTaskVo = new ScheduleTaskVo();
        scheduleTaskVo.setTaskName(task.getName());
        scheduleTaskVo.setTaskId(task.getId());
        scheduleTaskVo.setCronExpression(task.getCronExpression());
        scheduleTaskVo.setDesc(task.getDescription());
        scheduleTaskVo.setTaskGroup(task.getTaskGroup());
        scheduleTaskVo.setJobClass(task.getJobClass());
        scheduleTaskVo.setStatus(task.getStatus());
        scheduleTaskVo.setNextRunningTime(task.getNextRunningTime() != null ? formatter.format(task.getNextRunningTime()) : null);
        scheduleTaskVo.setPreRunningTime(task.getPreRunningTime() != null ? formatter.format(task.getPreRunningTime()) : null);
        scheduleTaskVo.setRepeatInterval(task.getRepeatInterval() != null ? task.getRepeatInterval().intValue() : 0);
        scheduleTaskVo.setType(task.getType() != null ? task.getType().intValue() : 1);
        return scheduleTaskVo;
    }

    @Override
    public TaskStateEnum startTask(Long taskId) throws ServiceException {
        logger.info("startTask:taskId = " + taskId);
        ScheduleTask scheduleTask = null;
        try {
            scheduleTask = scheduleTaskMapper.selectByPrimaryKey(taskId);
            ScheduleTaskVo scheduleTaskVo = this.convertScheduleTask(scheduleTask);
            quartzTaskHelper.scheduleTask(scheduleTaskVo);
            scheduleTask.setStatus(TaskStateEnum.NORMAL.name());
            scheduleTaskMapper.updateByPrimaryKeySelective(scheduleTask);
            logger.info("startTask success");
            return TaskStateEnum.NORMAL;
        } catch (Exception e) {
            logger.error("startTask failed :taskId = " + taskId, e);
            throw new ServiceException(13,"启动任务失败:"+e.getMessage());         
        }
    }

    @Override
    public TaskStateEnum pauseTask(Long taskId) throws ServiceException {
        logger.info("pauseTask:taskId = " + taskId);
        ScheduleTask scheduleTask = null;
        try {
            scheduleTask = scheduleTaskMapper.selectByPrimaryKey(taskId);
            ScheduleTaskVo scheduleTaskVo = this.convertScheduleTask(scheduleTask);
            if (!checkIsPlanning(scheduleTaskVo)) {
                throw new ServiceException(11,"该任务不在计划中");
            }
            if (checkIsRunning(scheduleTaskVo)) {
            	throw new ServiceException(12,"该任务正在运行");
            }
            quartzTaskHelper.pauseTask(scheduleTaskVo);
            scheduleTask.setStatus(TaskStateEnum.PAUSED.name());
            scheduleTaskMapper.updateByPrimaryKeySelective(scheduleTask);
            return TaskStateEnum.PAUSED;
        } catch (SchedulerException e) {
            logger.error("pauseTask failed:taskId = " + taskId, e);
            throw new ServiceException(13,"暂停任务失败:"+e.getMessage());            
        }
    }

    @Override
    public TaskStateEnum resumeTask(Long taskId) throws ServiceException {
        logger.info("resumeTask:taskId = " + taskId);
        ScheduleTask scheduleTask = null;
        try {
            scheduleTask = scheduleTaskMapper.selectByPrimaryKey(taskId);
            ScheduleTaskVo scheduleTaskVo = this.convertScheduleTask(scheduleTask);
            if (!checkIsPlanning(scheduleTaskVo)) {
            	throw new ServiceException(11,"该任务不在计划中");
            }
            if (checkIsRunning(scheduleTaskVo)) {
            	throw new ServiceException(12,"该任务正在运行");
            }
            quartzTaskHelper.resumeTask(scheduleTaskVo);
            scheduleTask.setStatus(TaskStateEnum.NORMAL.name());
            scheduleTaskMapper.updateByPrimaryKeySelective(scheduleTask);
            return TaskStateEnum.NORMAL;
        } catch (SchedulerException e) {
            logger.error("resumeTask failed:taskId = " + taskId, e);
            throw new ServiceException(13,"重启任务失败:"+e.getMessage());           
        }
    }

    @Override
    public TaskStateEnum stopTask(Long taskId) throws ServiceException {
        logger.info("stopTask:taskId = " + taskId);
        ScheduleTask scheduleTask = null;
        try {
            scheduleTask = scheduleTaskMapper.selectByPrimaryKey(taskId);
            ScheduleTaskVo scheduleTaskVO = this.convertScheduleTask(scheduleTask);
            if (!checkIsPlanning(scheduleTaskVO)) {
            	throw new ServiceException(11,"该任务不在计划中");
            }
            if (checkIsRunning(scheduleTaskVO)) {
            	throw new ServiceException(12,"该任务正在运行");
            }
            quartzTaskHelper.stopTask(scheduleTaskVO);
            scheduleTask.setStatus(TaskStateEnum.NONE.name());
            scheduleTaskMapper.updateByPrimaryKeySelective(scheduleTask);
            return TaskStateEnum.STOPPED;
        } catch (SchedulerException e) {
            logger.error("stopTask failed:taskId = " + taskId, e);
            throw new ServiceException(13,"停止任务失败:"+e.getMessage());     
        }
    }

    @Override
    public TaskStateEnum deleteTask(Long taskId) throws ServiceException {
        logger.info("stopTask:taskId = " + taskId);
        ScheduleTask scheduleTask = null;
        try {
            scheduleTask = scheduleTaskMapper.selectByPrimaryKey(taskId);
            ScheduleTaskVo scheduleTaskVO = this.convertScheduleTask(scheduleTask);
            if (checkIsPlanning(scheduleTaskVO)) {
            	throw new ServiceException(11,"该任务不在计划中");
            }
            if (checkIsRunning(scheduleTaskVO)) {
            	throw new ServiceException(12,"该任务正在运行");
            }
            scheduleTaskMapper.deleteByPrimaryKey(taskId);
            return TaskStateEnum.DELETED;
        } catch (SchedulerException e) {
            logger.error("deleteTask failed:taskId = " + taskId, e);
            throw new ServiceException(13,"删除任务失败:"+e.getMessage());   
        }
    }

    @Override
    public Boolean runOnceTask(Long taskId) throws ServiceException {
        logger.info("stopTask:taskId = " + taskId);
        ScheduleTask scheduleTask = null;
        try {
            scheduleTask = scheduleTaskMapper.selectByPrimaryKey(taskId);
            ScheduleTaskVo scheduleTaskVO = this.convertScheduleTask(scheduleTask);
            quartzTaskHelper.runOnceTask(scheduleTaskVO);
            return true;
        } catch (SchedulerException e) {
            logger.error("runOnceTask failed:taskId = " + taskId, e);
            throw new ServiceException(13,"执行一次任务失败:"+e.getMessage());
        }
    }
    
    private boolean checkIsRunning(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        List<ScheduleTaskVo> list = quartzTaskHelper.getRunningTasks();
        for (ScheduleTaskVo task : list) {
            if (task.getTaskName().equals(scheduleTaskVO.getTaskName())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIsPlanning(ScheduleTaskVo scheduleTaskVo) throws SchedulerException {
        List<ScheduleTaskVo> list = quartzTaskHelper.getPlanningTasks();
        for (ScheduleTaskVo task : list) {
            if (task.getTaskName().equals(scheduleTaskVo.getTaskName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doExecuteJob(Job executeJob, JobExecutionContext context, Object service) {
        // 记录Job执行状态和时间
        String classSimpleName = executeJob.getClass().getSimpleName();
        ScheduleTaskVo scheduleTaskVo = (ScheduleTaskVo) context.getJobDetail().getJobDataMap().get(executeJob.getClass().getCanonicalName());
        scheduleTaskVo.setStatus(TaskStateEnum.RUNNING.name());
        this.saveOrUpdateTask(scheduleTaskVo);
        try {
            logger.info("begin " + classSimpleName);
            MethodUtils.invokeMethod(service, EXECUTE_JOB);
            /*Method[] methods = service.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("executeJob")) {
                    method.invoke(service);
                    break;
                }
            }*/
            logger.info("end " + classSimpleName);
            scheduleTaskVo.setStatus(TaskStateEnum.NORMAL.name());
        } catch (Exception e) {
            scheduleTaskVo.setStatus(TaskStateEnum.ERROR.name());
            logger.error("execute " + classSimpleName + " failed", e);
        } finally {
            this.saveOrUpdateTask(scheduleTaskVo);
        }
    }
}
