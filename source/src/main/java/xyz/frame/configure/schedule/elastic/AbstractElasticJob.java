package xyz.frame.configure.schedule.elastic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import xyz.frame.pojo.common.TaskStateEnum;
import xyz.frame.pojo.vo.ScheduleTaskVo;
import xyz.frame.service.schedule.ScheduleTaskService;

public abstract class AbstractElasticJob implements SimpleJob {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Override
    public void execute(ShardingContext shardingContext){
        String classSimpleName = this.getClass().getSimpleName();
        ScheduleTaskVo scheduleTaskVO = JSON.parseObject(shardingContext.getJobParameter(), ScheduleTaskVo.class);

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
        } finally {
            scheduleTaskService.saveOrUpdateTask(scheduleTaskVO);
        }
    }

    /**
     * 任务执行内容
     */
    public abstract void doJob();
}
