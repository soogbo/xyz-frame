package xyz.frame.configure.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.common.TaskStateEnum;
import xyz.frame.entity.ScheduleTask;
import xyz.frame.mapper.ScheduleTaskMapper;
import xyz.frame.utils.ServiceException;

@Service("scheduleTaskService")
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTaskServiceImpl.class);
    
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ScheduleTaskMapper scheduleTaskMapper;

    @Autowired
    private QuartzTaskHelper quartzTaskHelper;
    
    @Override
    public List<ScheduleTaskVO> getAllTask() {
        logger.info("begin getAllTask...");
        List<ScheduleTask> tasks = scheduleTaskMapper.findScheduleTaskList();
        if (tasks != null) {
            List<ScheduleTaskVO> list = new ArrayList<ScheduleTaskVO>();
            for (ScheduleTask task : tasks) {
                ScheduleTaskVO scheduleTaskVO = this.convertScheduleTask(task);
                list.add(scheduleTaskVO);
            }
            logger.info("allTasks:"+ tasks);
            return list;
        }
        return null;
    }

    @Override
    public void saveOrUpdateTask(ScheduleTaskVO task) {
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
    }

    @Override
    public ScheduleTaskVO getOneTask(Long taskId) {
        logger.info("getOneTask:taskId" + taskId);
        ScheduleTask scheduleTask = scheduleTaskMapper.selectByPrimaryKey(taskId);
        return this.convertScheduleTask(scheduleTask);
    }
    
    /**
     * 实体类转换ScheduleTask->ScheduleTaskVO
     */
    private ScheduleTaskVO convertScheduleTask(ScheduleTask task) {
        ScheduleTaskVO scheduleTaskVO = new ScheduleTaskVO();
        scheduleTaskVO.setTaskName(task.getName());
        scheduleTaskVO.setTaskId(task.getId());
        scheduleTaskVO.setCronExpression(task.getCronExpression());
        scheduleTaskVO.setDesc(task.getDescription());
        scheduleTaskVO.setTaskGroup(task.getTaskGroup());
        scheduleTaskVO.setJobClass(task.getJobClass());
        scheduleTaskVO.setStatus(task.getStatus());
        scheduleTaskVO.setNextRunningTime(task.getNextRunningTime() != null ? formatter.format(task.getNextRunningTime()) : null);
        scheduleTaskVO.setPreRunningTime(task.getPreRunningTime() != null ? formatter.format(task.getPreRunningTime()) : null);
        scheduleTaskVO.setRepeatInterval(task.getRepeatInterval() != null ? task.getRepeatInterval().intValue() : 0);
        scheduleTaskVO.setType(task.getType() != null ? task.getType().intValue() : 1);
        return scheduleTaskVO;
    }

    @Override
    public TaskStateEnum startTask(Long taskId) throws ServiceException {
        logger.info("startTask:taskId = " + taskId);
        ScheduleTask scheduleTask = null;
        try {
            scheduleTask = scheduleTaskMapper.selectByPrimaryKey(taskId);
            ScheduleTaskVO scheduleTaskVO = this.convertScheduleTask(scheduleTask);
            quartzTaskHelper.scheduleTask(scheduleTaskVO);
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
            ScheduleTaskVO scheduleTaskVO = this.convertScheduleTask(scheduleTask);
            if (!checkIsPlanning(scheduleTaskVO)) {
                throw new ServiceException(11,"该任务不在计划中");
            }
            if (checkIsRunning(scheduleTaskVO)) {
            	throw new ServiceException(12,"该任务正在运行");
            }
            quartzTaskHelper.pauseTask(scheduleTaskVO);
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
            ScheduleTaskVO scheduleTaskVO = this.convertScheduleTask(scheduleTask);
            if (!checkIsPlanning(scheduleTaskVO)) {
            	throw new ServiceException(11,"该任务不在计划中");
            }
            if (checkIsRunning(scheduleTaskVO)) {
            	throw new ServiceException(12,"该任务正在运行");
            }
            quartzTaskHelper.resumeTask(scheduleTaskVO);
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
            ScheduleTaskVO scheduleTaskVO = this.convertScheduleTask(scheduleTask);
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
            ScheduleTaskVO scheduleTaskVO = this.convertScheduleTask(scheduleTask);
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
            ScheduleTaskVO scheduleTaskVO = this.convertScheduleTask(scheduleTask);
            quartzTaskHelper.runOnceTask(scheduleTaskVO);
            return true;
        } catch (SchedulerException e) {
            logger.error("runOnceTask failed:taskId = " + taskId, e);
            throw new ServiceException(13,"执行一次任务失败:"+e.getMessage());
        }
    }
    
    private boolean checkIsRunning(ScheduleTaskVO scheduleTaskVO) throws SchedulerException {
        List<ScheduleTaskVO> list = quartzTaskHelper.getRunningTasks();
        for (ScheduleTaskVO task : list) {
            if (task.getTaskName().equals(scheduleTaskVO.getTaskName())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIsPlanning(ScheduleTaskVO scheduleTaskVO) throws SchedulerException {
        List<ScheduleTaskVO> list = quartzTaskHelper.getPlanningTasks();
        for (ScheduleTaskVO task : list) {
            if (task.getTaskName().equals(scheduleTaskVO.getTaskName())) {
                return true;
            }
        }
        return false;
    }

}
