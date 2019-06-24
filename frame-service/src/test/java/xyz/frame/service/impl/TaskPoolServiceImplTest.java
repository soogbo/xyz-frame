/**
 * 
 */
package xyz.frame.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.frame.AbstractTest;
import xyz.frame.service.TaskPoolService;

/**
 * @Description
 * @author shisp
 * @date 2017年12月8日 下午1:36:59
 */
public class TaskPoolServiceImplTest extends AbstractTest {

    @Autowired
    private TaskPoolService taskPoolService;

    @Test
    public void testTestTaskPool() {
        taskPoolService.testTaskPool();

        // Assert.assertEquals(null, null); //junit 测试比较输出结果
        // Assert.assertNotNull(null);
    }

}
