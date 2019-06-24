/**
 * 
 */
package xyz.frame.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import xyz.frame.configure.SpringContextHolder;
import xyz.frame.exception.ServiceException;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description 测试batch任务执行
 * @author shisp
 * @date 2017年12月14日  下午1:14:37
 */

@RestController
@RequestMapping("/batch")
@Api(value = "测试batch任务执行")
public class BatchController {
    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);
    @Autowired
    private JobLauncher jobLauncher;

    @ApiOperation(value = "测试执行一次")
    @GetMapping(value = "/testBatch")
    public ResponseResult<?> testBatch() {
        try {
            jobLauncher.run((org.springframework.batch.core.Job) SpringContextHolder.getBean("importUserJob"), new JobParametersBuilder().addDate("date", new Date()).toJobParameters());
        } catch (Exception e) {
            logger.error("执行测试springbatch任务异常,{},{}",e.getMessage(),e);
            throw new ServiceException(1, "执行测试springbatch任务异常！");
        }
        return RestResultUtil.success();
    }
    
}
