/**
 * 
 */
package xyz.frame.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.frame.AbstractTest;
import xyz.frame.json.FrameJsonUtils;
import xyz.frame.mapper.ParamClassMapper;
import xyz.frame.pojo.po.ParamClass;
import xyz.frame.service.ParamClassService;

/**
 * @Description
 * @author shisp
 * @date 2018年2月5日 下午6:03:34
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @SpringBootTest(classes = TestSpringApplication.class)
// @ContextConfiguration(classes=TestSpringApplication.class)
public class ParamClassServiceImplTest extends AbstractTest {

    @Autowired
    private ParamClassService paramClassService;
    @Autowired
    private ParamClassMapper paramClassMapper;

    @Test
    public void testListAllParamClass() {
        List<ParamClass> list = paramClassService.listAllParamClass();
        System.out.println(FrameJsonUtils.toJson(list));

        ParamClass selectAll = paramClassMapper.selectByPrimaryKey(1L);
        System.out.println(FrameJsonUtils.toJson(selectAll));
    }

    @Test
    public void testGetByClassCode() {
        fail("Not yet implemented");
    }

    @Test
    public void testSaveOrUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteById() {
        fail("Not yet implemented");
    }

}
