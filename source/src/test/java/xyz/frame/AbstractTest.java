package xyz.frame;

import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试公共抽象部分
 * 
 */

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = TestSpringApplication.class)
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestSpringApplication.class)
public abstract class AbstractTest {
    protected Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    /**
     * @method setUp(测试前初始化相关对象)
     * @return void
     * @author ZHY
     * @throws UnsupportedEncodingException
     */
    @Before
    public void setUp() throws UnsupportedEncodingException {
        logger.info("before junit test: {}", System.currentTimeMillis());
    }

    /**
     * @method destroy(测试结束后释放对象)
     * @return void
     * @author ZHY
     */
    @After
    public void destroy() {
        logger.info("after junit test: {}", System.currentTimeMillis());
    }
}
