/**
 * 
 */
package xyz.frame.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.frame.TestSpringApplication;
import xyz.frame.service.TaskPoolService;

/**
 * @Description 
 * @author shisp
 * @date 2017年12月8日  下午1:36:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=TestSpringApplication.class)
@SpringBootTest(classes = TestSpringApplication.class)
public class TaskPoolServiceImplTest {

    @Autowired
    private TaskPoolService taskPoolService;
    
    @Test
    public void testTestTaskPool() {
        taskPoolService.testTaskPool();
        
//        Assert.assertEquals(null, null); //junit 测试比较输出结果
//      Assert.assertNotNull(null);
    }

}
