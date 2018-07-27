package xyz.frame.rbac.service;

import java.util.List;

import xyz.frame.rbac.pojo.po.RbacPermission;

/**
 * RbacPermission
 * 
 * @author shisp
 * @date 2018-7-27 13:40:38
 */
public interface RbacPermissionService {

    /**
     * 查询用户权限
     * 
     * @param userId
     * @return
     */
    List<RbacPermission> listPermissionByUserId(Long userId);
}
