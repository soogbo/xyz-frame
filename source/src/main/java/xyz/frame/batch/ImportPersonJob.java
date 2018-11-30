package xyz.frame.batch;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * batch 插入数据job 没有此job时，项目启动会自动执行一次
 * 
 * @author shisp
 * @date 2018-8-02 10:31:24
 */
@Component
@EnableScheduling
public class ImportPersonJob {
    private static final Logger logger = LoggerFactory.getLogger(ImportPersonJob.class);

    @Resource(name = "importUserJob")
    private Job importUserJob;
    @Autowired
    private JobLauncher jobLauncher;
// 注解注入时间表达式，定时执行job
//    @Scheduled(cron = "1/10 * * * * ? ")
    public void run() {
        try {
            JobExecution execution = jobLauncher.run(importUserJob, new JobParameters());
            logger.info("Execution status: {}", execution.getStatus());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            logger.error("Execution status error: job name={},", importUserJob.getName(), e);
        }
    }
}