package xyz.frame.rbac.service;

import xyz.frame.rbac.pojo.po.RbacUser;

/**
 * RbacUser
 * 
 * @author shisp
 * @date 2018-7-27 13:36:10
 */
public interface RbacUserService {

    /**
     * username查询用户信息
     * 
     * @param username
     * @return
     */
    RbacUser getByUsername(String username);
}
