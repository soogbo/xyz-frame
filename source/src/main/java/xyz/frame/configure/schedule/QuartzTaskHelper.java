package xyz.frame.configure.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.simpl.SimpleClassLoadHelper;
import org.quartz.spi.ClassLoadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import xyz.frame.pojo.vo.ScheduleTaskVo;
import xyz.frame.service.schedule.ScheduleTaskService;


/**
 * Created by qiuxinjie on 14-11-26.
 */
@Component
public class QuartzTaskHelper {

    public enum TaskState {NONE, NORMAL, PAUSED, RUNNING, DELETED, STOPPED, ERROR}

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Value("${task.run.flag}")
    private Boolean taskRunFlag;

    @PostConstruct
    public void initTask() throws Exception {
        if (!taskRunFlag) {
            return;
        }
        if (schedulerFactoryBean == null) {
            return;
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        List<ScheduleTaskVo> taskList = scheduleTaskService.getAllTask();

        ClassLoadHelper helper = new SimpleClassLoadHelper();

        for (ScheduleTaskVo scheduleTaskVO : taskList) {
        	//重启时把RUNNING置为NORMAL
        	if(QuartzTaskHelper.TaskState.RUNNING.name().equals(scheduleTaskVO.getStatus())){
        		scheduleTaskVO.setStatus(Trigger.TriggerState.NORMAL.name());	
        	}
        	
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup());

            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            if (null == trigger) {
            	Class<? extends Job> jobClass = null;
            	try{
            		jobClass = helper.loadClass(scheduleTaskVO.getJobClass(),Job.class);	
            	}catch(ClassNotFoundException ex){
            		jobClass = null;
            	}            
            	if(jobClass==null){
            		continue;
            	}
                JobDetail jobDetail = JobBuilder.newJob(jobClass)
                        .withIdentity(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup()).build();
                jobDetail.getJobDataMap().put(scheduleTaskVO.getJobClass(), scheduleTaskVO);

                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleTaskVO.getCronExpression());

                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup()).withSchedule(scheduleBuilder).build();

                if (Trigger.TriggerState.NORMAL.name().equals(scheduleTaskVO.getStatus())) {
                    scheduler.scheduleJob(jobDetail, trigger);
                } else if (Trigger.TriggerState.PAUSED.name().equals(scheduleTaskVO.getStatus())) {
                    scheduler.scheduleJob(jobDetail, trigger);
                    JobKey jobKey = JobKey.jobKey(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup());
                    scheduler.pauseJob(jobKey);
//                    scheduler.
                }

            } else {
                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleTaskVO.getCronExpression());

                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();

                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }

    }

    public void scheduleTask(ScheduleTaskVo scheduleTaskVO) throws SchedulerException, ClassNotFoundException {
        ClassLoadHelper helper = new SimpleClassLoadHelper();

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);        
		JobDetail jobDetail = JobBuilder.newJob(helper.loadClass(scheduleTaskVO.getJobClass(),Job.class))
                .withIdentity(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup()).build();
        jobDetail.getJobDataMap().put(scheduleTaskVO.getJobClass(), scheduleTaskVO);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleTaskVO.getCronExpression());

        //按新的cronExpression表达式构建一个新的trigger
        trigger = TriggerBuilder.newTrigger().withIdentity(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup()).withSchedule(scheduleBuilder).build();

        scheduler.scheduleJob(jobDetail, trigger);

    }

    /**
     * 暂停任务
     */
    public void pauseTask(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 回复任务
     */
    public void resumeTask(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 停止任务
     */
    public void stopTask(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup());
        scheduler.deleteJob(jobKey);
    }

    /**
     * 运行一次
     */
    public void runOnceTask(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 重新设置运行时间
     */
    public void rescheduleJob(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleTaskVO.getTaskName(), scheduleTaskVO.getTaskGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleTaskVO.getCronExpression());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        //按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    public List<ScheduleTaskVo> getRunningTasks() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleTaskVo> jobList = new ArrayList<ScheduleTaskVo>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            ScheduleTaskVo job = new ScheduleTaskVo();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setTaskName(jobKey.getName());
            job.setTaskGroup(jobKey.getGroup());
            job.setDesc("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    public List<ScheduleTaskVo> getPlanningTasks() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<ScheduleTaskVo> jobList = new ArrayList<ScheduleTaskVo>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                ScheduleTaskVo job = new ScheduleTaskVo();
                job.setTaskName(jobKey.getName());
                job.setTaskGroup(jobKey.getGroup());
                job.setDesc("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                job.setNextRunningTime(trigger.getNextFireTime() != null ? formatter.format(trigger.getNextFireTime()) : "");
                job.setPreRunningTime(trigger.getPreviousFireTime() != null ? formatter.format(trigger.getPreviousFireTime()) : "");
                jobList.add(job);
            }
        }
        return jobList;
    }
    
    public Date getNextFireTime(String taskName) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    if (jobKey.getName().equals(taskName)) {
                        Date nextFireTime = trigger.getNextFireTime();
                        return nextFireTime;
                    }
                }
            }
            return null;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
