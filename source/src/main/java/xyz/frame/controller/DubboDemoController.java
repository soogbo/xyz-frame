package xyz.frame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.service.DubboDemoService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description DubboDemo
 * @author shisp
 * @date 2018-2-28 09:54:14
 */
@RestController
public class DubboDemoController {

    @Autowired
    private DubboDemoService dubboDemoService;

    @GetMapping("/dubbo/consumer")
    public ResponseResult<?> consumer(@RequestParam("classCode") String classCode) {
        dubboDemoService.toConsumerService(classCode);
        return RestResultUtil.success(classCode);
    }

}
