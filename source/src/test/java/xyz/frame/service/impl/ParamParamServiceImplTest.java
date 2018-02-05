package xyz.frame.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.frame.TestSpringApplication;
import xyz.frame.json.FrameJsonUtils;
import xyz.frame.mapper.ParamParamMapper;
import xyz.frame.pojo.po.ParamParam;
import xyz.frame.service.ParamParamService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestSpringApplication.class)
public class ParamParamServiceImplTest {

    @Autowired
    private ParamParamService paramParamService;
    @Autowired
    private ParamParamMapper paramParamMapper;
    
    @Test
    public void testFindListByClassCode() {
        List<ParamParam> list = paramParamService.findListByClassCode(Arrays.asList("qc_config"));
        System.out.println(FrameJsonUtils.toJson(list));
    }
    @Test
    public void updateClassIdToNull() {
        int i = paramParamMapper.updateClassIdToNull(1L);
        System.out.println(i);
    }
    @Test
    public void selectAll() {
        List<ParamParam> list = paramParamMapper.selectAll();
        System.out.println(FrameJsonUtils.toJson(list));
    }

}
