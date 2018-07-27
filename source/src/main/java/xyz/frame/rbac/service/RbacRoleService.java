package xyz.frame.rbac.service;

import java.util.List;

import xyz.frame.rbac.pojo.po.RbacRole;

/**
 * RbacRole
 * 
 * @author shisp
 * @date 2018-7-27 13:58:32
 */
public interface RbacRoleService {

    /**
     * 获取用户所有角色
     * 
     * @param userId
     * @return
     */
    List<RbacRole> listRoleByUserId(Long userId);
}
