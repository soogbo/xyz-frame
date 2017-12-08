package xyz.frame.service.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import xyz.frame.service.TaskPoolService;

/**
 * @Description 
 * @author shisp
 * @date 2017年11月28日  下午8:12:41
 */
@Service("taskPoolService")
public class TaskPoolServiceImpl implements TaskPoolService {

    private static final Logger logger = LoggerFactory.getLogger(TaskPoolServiceImpl.class); 
    
    @Resource(name="commonTaskExecutor")
    private TaskExecutor taskExecutor;

    @Override
    public void testTaskPool() {
        for (int i = 0; i < 10; i++) {
            logger.info("for i={}", i);
            final int t = i;
            taskExecutor.execute(()->{
                logger.info("for i={}", t);
            });
        }
    }
    

}
