/**
 * 
 */
package xyz.frame.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import xyz.frame.TestSpringApplication;
import xyz.frame.service.TaskPoolService;

/**
 * @Description 
 * @author shisp
 * @date 2017年12月8日  下午1:36:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestSpringApplication.class)
public class TaskPoolServiceImplTest {

    /**
     * Test method for {@link xyz.frame.service.impl.TaskPoolServiceImpl#testTaskPool()}.
     */
    
    @Autowired
    private TaskPoolService taskPoolService;
    
    @Test
    public void testTestTaskPool() {
        taskPoolService.testTaskPool();
        
//        Assert.assertEquals(null, null); //junit 测试比较输出结果
    }

}
