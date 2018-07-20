package xyz.frame.configure.schedule.elastic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import xyz.frame.pojo.vo.ScheduleTaskVo;
import xyz.frame.service.schedule.ScheduleTaskService;


/**
 * @author shisp
 * @date 2018-4-26 19:38:23
 */
//@Component
public class ElasticJobTaskHelper implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticJobTaskHelper.class);

    public enum TaskState {NONE, NORMAL, PAUSED, RUNNING, DELETED, STOPPED, ERROR}

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Autowired
    private JobEventRdbConfiguration jobEventRdbConfiguration;

    private ApplicationContext applicationContext;

    @Value("${task.run.flag}")
    private Boolean taskRunFlag;

    @PostConstruct
    public void initTask() throws Exception {
        if (!taskRunFlag) {
            return;
        }
        //todo 需要考虑集群环境下如何启动一个新任务
        List<ScheduleTaskVo> taskList = scheduleTaskService.getAllTask();
        for (ScheduleTaskVo scheduleTaskVO : taskList) {
            try {
                String status = scheduleTaskVO.getStatus();
                //兼容老数据，已经停止、暂停的任务不启动
                if (TaskState.NORMAL.name().equals(status) || TaskState.RUNNING.name().equals(status) || TaskState.ERROR.name().equals(status)) {
                    scheduleTask(scheduleTaskVO);
                }
            } catch (ClassNotFoundException e) {
                LOGGER.info("class not found:{}", scheduleTaskVO.getJobClass());
            } catch (NoSuchBeanDefinitionException e) {
                LOGGER.info("there has no bean in spring IOC:{}", scheduleTaskVO.getJobClass());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void scheduleTask(ScheduleTaskVo scheduleTaskVO) throws ClassNotFoundException, NoSuchBeanDefinitionException {
        String jobClass = scheduleTaskVO.getJobClass();
        Class<ElasticJob> clazz = (Class<ElasticJob>) Class.forName(jobClass);
        //从Spring容器中获取实例，不要自己new一个实例，因为job中使用了@Autowired注解
        ElasticJob job = applicationContext.getBean(clazz);
        LiteJobConfiguration config = jobConfiguration(scheduleTaskVO.getTaskName(), scheduleTaskVO.getCronExpression(),
                JSON.toJSONString(scheduleTaskVO), scheduleTaskVO.getDesc(), jobClass);
        new SpringJobScheduler(job, zookeeperRegistryCenter, config, jobEventRdbConfiguration).init();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取job配置
     *
     * @param jobName
     * @param cron
     * @param jobParameter
     * @param jobDesc
     * @param jobClass
     * @return
     */
    private LiteJobConfiguration jobConfiguration(String jobName, String cron, String jobParameter, String jobDesc, String jobClass) {
        JobCoreConfiguration jobCoreConfig = JobCoreConfiguration.newBuilder(jobName, cron, Constants.DEFAULT_SHARDING_COUNT)
                .jobParameter(jobParameter)
                .description(jobDesc)
                //支持失败转移
                .failover(true)
                .build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfig, jobClass);
        //不使用overwrite方法覆盖注册中心的配置
        return LiteJobConfiguration.newBuilder(simpleJobConfiguration).build();
    }
    
    /**
     * 暂停任务
     * 请使用elastic-job的控制台进行操作
     */
    public void pauseTask(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        //请使用elastic-job的控制台进行操作
    }

    /**
     * 回复任务
     * 请使用elastic-job的控制台进行操作
     */
    public void resumeTask(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        //请使用elastic-job的控制台进行操作
    }

    /**
     * 停止任务
     * 请使用elastic-job的控制台进行操作
     */
    public void stopTask(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        //请使用elastic-job的控制台进行操作
    }

    /**
     * 运行一次
     * 请使用elastic-job的控制台进行操作
     */
    public void runOnceTask(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        //请使用elastic-job的控制台进行操作
    }

    /**
     * 重新设置运行时间
     * 请使用elastic-job的控制台进行操作
     */
    public void rescheduleJob(ScheduleTaskVo scheduleTaskVO) throws SchedulerException {
        //请使用elastic-job的控制台进行操作
    }

    /**
     * 获取运行的任务
     * 请使用elastic-job的控制台进行操作
     *
     * @return
     * @throws SchedulerException
     */
    public List<ScheduleTaskVo> getRunningTasks() throws SchedulerException {
        //请使用elastic-job的控制台进行操作
        return new ArrayList<>();
    }

    public List<ScheduleTaskVo> getPlanningTasks() throws SchedulerException {
        //请使用elastic-job的控制台进行操作
        return new ArrayList<>();
    }

    public Date getNextFireTime(String taskName) {
        //elastic-job控制台能修改cron表达式，无法准确的获取下一次执行时间
        return null;
    }

}
