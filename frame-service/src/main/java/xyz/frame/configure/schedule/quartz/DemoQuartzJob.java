package xyz.frame.configure.schedule.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.frame.service.TestScheduleService;
import xyz.frame.service.schedule.ScheduleTaskService;

/**
 * @Description quartz jobDemo
 * @author shisp
 * @date 2017年12月14日  下午12:44:40
 */
@DisallowConcurrentExecution
public class DemoQuartzJob extends AbstractQuartzJob {
    @Autowired
    private ScheduleTaskService scheduleTaskService;
    @Autowired
    private TestScheduleService service;

    // 方法1：使用反射执行指定方法名executeJob的任务，service方法名需固定为executeJob无参；不推荐
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        scheduleTaskService.doExecuteJob(this, context, service);
    }

    // 方法2：使用抽象方法封装执行job前后的操作
    @Override
    public void doJob() {
        service.executeJob();
    }

}
