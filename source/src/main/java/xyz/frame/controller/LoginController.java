package xyz.frame.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.utils.GeneralResponse;

/**
 * @author shisp
 * @date 2018-7-27 15:57:57
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录验证
     * 
     * @param resp
     * @param username
     * @param token
     * @return
     */
    @PostMapping(value = "/login")
    public GeneralResponse<String> login(HttpServletResponse resp, @RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "password", required = true) String password) {
        AuthenticationToken authenticationToken = new UsernamePasswordToken(username, password, null);

        Subject subject = SecurityUtils.getSubject();
        if (subject.getSession(false) != null) {
            subject.logout();
        }
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            logger.info("登录失败", e);
            return GeneralResponse.fail("请先登录!");
        }
        return GeneralResponse.success((String) SecurityUtils.getSubject().getPrincipal());
    }
}
