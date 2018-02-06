package xyz.frame.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import xyz.frame.pojo.po.User;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

@RestController
@RequestMapping("/api/user")
@Api(value = "用户接口")
public class SwaggerController {
    private static final Logger logger = LoggerFactory.getLogger(SwaggerController.class);

    @ApiOperation(value = "用户登陆")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<?> login(@RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "password", defaultValue = "") String password) {
        logger.info("swagger ui api test !!!");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return RestResultUtil.success(user);
    }
}