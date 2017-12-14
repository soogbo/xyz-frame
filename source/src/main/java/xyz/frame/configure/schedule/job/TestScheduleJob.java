package xyz.frame.configure.schedule.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.frame.pojo.common.TaskStateEnum;
import xyz.frame.pojo.vo.ScheduleTaskVo;
import xyz.frame.service.TestScheduleService;
import xyz.frame.service.schedule.ScheduleTaskService;

/**
 * @Description jobDemo
 * @author shisp
 * @date 2017年12月14日  下午12:44:40
 */
@DisallowConcurrentExecution
public class TestScheduleJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(TestScheduleJob.class);

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Autowired
    private TestScheduleService service;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 记录Job执行状态和时间
        String classSimpleName = this.getClass().getSimpleName();
        ScheduleTaskVo scheduleTaskVo = (ScheduleTaskVo) context.getJobDetail().getJobDataMap().get(this.getClass().getCanonicalName());
        scheduleTaskVo.setStatus(TaskStateEnum.RUNNING.name());
        scheduleTaskService.saveOrUpdateTask(scheduleTaskVo);
        try {
            logger.info("begin " + classSimpleName);
            service.execute();
            logger.info("end " + classSimpleName);
            scheduleTaskVo.setStatus(TaskStateEnum.NORMAL.name());
        } catch (Exception e) {
            scheduleTaskVo.setStatus(TaskStateEnum.ERROR.name());
            logger.error("execute " + classSimpleName + " failed", e);
        } finally {
            scheduleTaskService.saveOrUpdateTask(scheduleTaskVo);
        }
    }

}
