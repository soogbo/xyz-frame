package xyz.frame.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import xyz.frame.exception.ServiceException;
import xyz.frame.pojo.common.ResultEnum;
import xyz.frame.rbac.pojo.po.RbacUser;

/**
 * 系统登录用户工具
 * 
 * @author shisp
 * @date 2018-8-02 09:20:06
 */
public class RbacUserUtils {
    private RbacUserUtils() {
    }

    private static final String CURRENT_USER = "CURRENT_USER";

    /**
     * 获取当前登录用户
     *
     * @return 登录用户
     */
    public static RbacUser getCurrentUser() {
        return (RbacUser) getSession().getAttribute(CURRENT_USER);
    }

    /**
     * 获取当前登录用户Id
     *
     * @return 登录用户
     */
    public static Long getCurrentUserId() {
        RbacUser rbacUser = getCurrentUser();

        if (rbacUser == null) {
            throw new ServiceException(ResultEnum.LOGIN_USER_ERROR);
        }

        return rbacUser.getId();
    }

    /**
     * 设置当前登录用户
     *
     * @param rbacUser
     *            登录用户
     */
    public static void setUserToSession(RbacUser rbacUser) {
        getSession().setAttribute(CURRENT_USER, rbacUser);
    }

    /**
     * 获取当前shiro session
     *
     * @return 当前session
     */
    private static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

}
