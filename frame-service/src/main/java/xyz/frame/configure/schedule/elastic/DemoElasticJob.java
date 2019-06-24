package xyz.frame.configure.schedule.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xyz.frame.service.TestScheduleService;

/**
 * @Description quartz jobDemo
 * @author shisp
 * @date 2017年12月14日  下午12:44:40
 */
//@Component
public class DemoElasticJob extends AbstractElasticJob {
    @Autowired
    private TestScheduleService service;

    // 方法2：使用抽象方法封装执行job前后的操作
    @Override
    public void doJob() {
        service.executeJob();
    }

}
