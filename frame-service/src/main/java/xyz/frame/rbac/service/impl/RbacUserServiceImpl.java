package xyz.frame.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.rbac.mapper.RbacUserMapper;
import xyz.frame.rbac.pojo.po.RbacUser;
import xyz.frame.rbac.service.RbacUserService;

@Service
public class RbacUserServiceImpl implements RbacUserService {

    @Autowired
    private RbacUserMapper rbacUserMapper;
    
    @Override
    public RbacUser getByUsername(String username) {
        return rbacUserMapper.selectByUsername(username);
    }

}
