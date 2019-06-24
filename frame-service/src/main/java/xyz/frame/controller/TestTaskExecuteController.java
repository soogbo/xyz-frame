package xyz.frame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.service.TaskPoolService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description 页面调用线程池测试
 * @author shisp
 * @date 2017年12月8日  下午1:54:12
 */
@RestController
@RequestMapping("/test")
public class TestTaskExecuteController {

	@Autowired
	private TaskPoolService taskPoolService;

	@GetMapping(value = "/taskPoolTest")
	public ResponseResult<?> taskPoolTest() {
	    taskPoolService.testTaskPool();
		return RestResultUtil.success();
	}
}
