package xyz.frame.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.pojo.po.UserPo;
import xyz.frame.service.UserService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * 页面调用发送mq测试Controller
 * 
 * Created by shisp on 2017年11月24日.
 */
@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/all")
    public ResponseResult<?> submitTestMqProducer() {
        List<UserPo> findAllUser = userService.findAllUser();
        return RestResultUtil.success(findAllUser);
    }
}
