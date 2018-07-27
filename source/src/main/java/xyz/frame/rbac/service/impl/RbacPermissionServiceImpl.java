package xyz.frame.rbac.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.rbac.mapper.RbacPermissionMapper;
import xyz.frame.rbac.pojo.po.RbacPermission;
import xyz.frame.rbac.pojo.po.RbacRole;
import xyz.frame.rbac.service.RbacPermissionService;
import xyz.frame.rbac.service.RbacRoleService;

@Service
public class RbacPermissionServiceImpl implements RbacPermissionService {

    @Autowired
    private RbacRoleService rbacRoleService;
    @Autowired
    private RbacPermissionMapper rbacPermissionMapper;
    
    @Override
    public List<RbacPermission> listPermissionByUserId(Long userId) {
        List<RbacRole> roleList = rbacRoleService.listRoleByUserId(userId);
        List<Long> roleIdList = new ArrayList<>();
        if (!roleList.isEmpty()) {
            roleList.forEach(r -> roleIdList.add(r.getId()));
        }
        return rbacPermissionMapper.selectByRoleIds(roleIdList);
    }

}
