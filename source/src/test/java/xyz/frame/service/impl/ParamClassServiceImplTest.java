/**
 * 
 */
package xyz.frame.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.frame.TestSpringApplication;
import xyz.frame.json.FrameJsonUtils;
import xyz.frame.pojo.po.ParamClass;
import xyz.frame.service.ParamClassService;

/**
 * @Description 
 * @author shisp
 * @date 2018年2月5日  下午6:03:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=TestSpringApplication.class)
@SpringBootTest(classes = TestSpringApplication.class)
public class ParamClassServiceImplTest {

    @Autowired
    private ParamClassService paramClassService;
    
    @Test
    public void testListAllParamClass() {
        List<ParamClass> list = paramClassService.listAllParamClass();
        System.out.println(FrameJsonUtils.toJson(list));
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
