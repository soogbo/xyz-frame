package xyz.frame.configure.schedule.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.frame.service.TestScheduleService;
import xyz.frame.service.schedule.ScheduleTaskService;

/**
 * @Description jobDemo
 * @author shisp
 * @date 2017年12月14日  下午12:44:40
 */
@DisallowConcurrentExecution
public class TestScheduleJob implements Job {
    @Autowired
    private ScheduleTaskService scheduleTaskService;
    @Autowired
    private TestScheduleService service;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        scheduleTaskService.doExecuteJob(this, context, service);
    }

}
