/**
 * 
 */
package xyz.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.service.TaskPoolService;
import xyz.frame.service.TestScheduleService;

/**
 * @Description 
 * @author shisp
 * @date 2017年12月14日  下午12:47:00
 */
@Service("testScheduleService")
public class TestScheduleServiceImpl implements TestScheduleService {

    @Autowired
    private TaskPoolService taskPoolService;
    
    @Override
    public void executeJob() {
        taskPoolService.testTaskPool();
    }

}
