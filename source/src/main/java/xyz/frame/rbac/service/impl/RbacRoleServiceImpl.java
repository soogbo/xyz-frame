package xyz.frame.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.rbac.mapper.RbacRoleMapper;
import xyz.frame.rbac.pojo.po.RbacRole;
import xyz.frame.rbac.service.RbacRoleService;

@Service
public class RbacRoleServiceImpl implements RbacRoleService {

    @Autowired
    private RbacRoleMapper rbacRoleMapper;
    
    @Override
    public List<RbacRole> listRoleByUserId(Long userId) {
        return rbacRoleMapper.selectByUserId(userId);
    }

}
