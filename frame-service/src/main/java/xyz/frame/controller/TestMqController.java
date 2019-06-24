package xyz.frame.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.service.impl.rocketmq.SubmitTestMqProducer;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * 页面调用发送mq测试Controller 
 * 
 * Created by shisp on 2017年11月24日.
 */
@RestController
@RequestMapping("/test")
public class TestMqController {

	@Autowired
	private SubmitTestMqProducer submitTestMqProducer;

	@GetMapping(value = "/submitTestMqProducer")
	public ResponseResult<?> submitTestMqProducer() {
		List<Integer> arrayList = new ArrayList<>();
		Collections.addAll(arrayList, 1, 2, 3, 4, 5, 6);
		submitTestMqProducer.sendOperationToMq(arrayList);
		return RestResultUtil.success(arrayList);
	}
}
