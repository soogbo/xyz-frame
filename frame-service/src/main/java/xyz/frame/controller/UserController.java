package xyz.frame.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.json.FrameJsonUtils;
import xyz.frame.pojo.po.User;
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
//    public String userAll() {
        List<User> findAllUser = userService.findAllUser();
        return RestResultUtil.success(findAllUser);
//        return FrameJsonUtils.toJson(RestResultUtil.success(findAllUser));
    }
    @GetMapping(value = "/user/all/jsonString")
//    public ResponseResult<?> submitTestMqProducer() {
    public String userAllJsonString() {
    	List<User> findAllUser = userService.findAllUser();
//        return RestResultUtil.success(findAllUser);
    	return FrameJsonUtils.toJson(RestResultUtil.success(findAllUser));
    }
}
