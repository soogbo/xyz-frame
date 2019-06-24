package xyz.frame.configure.schedule.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.frame.pojo.common.TaskStateEnum;
import xyz.frame.pojo.vo.ScheduleTaskVo;
import xyz.frame.service.schedule.ScheduleTaskService;

public abstract class AbstractQuartzJob implements Job {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScheduleTaskService scheduleTaskService;
//    @Autowired
//    private ParamParamService paramParamService;
//    @Resource(name="monitorCenterProducer")
//    private MonitorCenterProducer monitorCenterProducer;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String classSimpleName = this.getClass().getSimpleName();
        ScheduleTaskVo scheduleTaskVO = (ScheduleTaskVo) jobExecutionContext.getJobDetail().getJobDataMap().get(this.getClass().getCanonicalName());
        scheduleTaskVO.setStatus(TaskStateEnum.RUNNING.name());
        scheduleTaskService.saveOrUpdateTask(scheduleTaskVO);
        try {
            logger.info("begin {}", classSimpleName);
            // 子类执行job
            this.doJob();

            logger.info("end {}", classSimpleName);
            scheduleTaskVO.setStatus(TaskStateEnum.NORMAL.name());
        } catch (Exception e) {
            scheduleTaskVO.setStatus(TaskStateEnum.ERROR.name());
            String alarmDetail = "跑批监控：执行任务 [" + classSimpleName + "] 出错！";
            logger.error(alarmDetail, e);
            
            /*ParamParam param = paramParamService.getParamByClassCodeAndParamCodeFromCacheOrDb(ClassCodeEnum.monitor_config.name(), ParamCodeEnum.system_health.name());
            if (null!=param && Constants.ZERO.equals(param.getParamValue())) {
                logger.info("已关闭，健康检查。");
                return;
            }*/
            
            // job异常发送至监控系统
            /*MonitorCenterMessage message = MonitorMessageUtils.getNewMessageInstance(
                    alarmDetail,
                    MonitorMessageUtils.AlarmLevel.ERROR.getLevel(), 
                    MonitorMessageUtils.BATCH);
            monitorCenterProducer.alarm(message);*/
        } finally {
            scheduleTaskService.saveOrUpdateTask(scheduleTaskVO);
        }
    }

    /**
     * 任务执行内容
     */
    public abstract void doJob();
}
