package xyz.frame.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.frame.pojo.entity.User;
import xyz.frame.service.UserService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * 页面调用发送mq测试Controller
 * 
 * Created by shisp on 2017年11月24日.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    public ResponseResult<?> submitTestMqProducer() {
        List<User> findAllUser = userService.findAllUser();
        return RestResultUtil.success(findAllUser);
    }
}
