package xyz.frame.rbac.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import xyz.frame.pojo.common.ValidEnum;
import xyz.frame.rbac.pojo.po.RbacPermission;
import xyz.frame.rbac.pojo.po.RbacRole;
import xyz.frame.rbac.pojo.po.RbacUser;
import xyz.frame.rbac.service.RbacPermissionService;
import xyz.frame.rbac.service.RbacRoleService;
import xyz.frame.rbac.service.RbacUserService;

/**
 * 自定义权限匹配和账号密码匹配
 * 
 * @author shisp
 * @date 2018-7-27 13:29:19
 */
//@Component("frameShiroRealm")
public class FrameShiroRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    private RbacUserService rbacUserService;
    @Lazy
    @Autowired
    private RbacRoleService rbacRoleService;
    @Lazy
    @Autowired
    private RbacPermissionService rbacPermissionService;
    
    /**
     * 用户基础权限
     */
    private static Set<String> basePermission = new TreeSet<>();

    static {
        basePermission.add("rbacUser.getUserInfo");
        basePermission.add("rbacUser.updatePassword");
        basePermission.add("param.*");
    }
    
    /* (non-Javadoc) 授权 获取权限
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // String username = (String) getAvailablePrincipal(principals);
        // RbacUser rbacUser = rbacUserService.getByUsername(username);
        RbacUser rbacUser = (RbacUser) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 获取所有的权限
        Set<String> permission = new HashSet<>();
        List<RbacPermission> permissionList = rbacPermissionService.listPermissionByUserId(rbacUser.getId());
        if (permissionList != null) {
            permissionList.forEach(p -> {
                if (StringUtils.isNotBlank(p.getPermission())) {
                    permission.add(p.getPermission());
                }
            });
        }
        permission.addAll(basePermission);
        info.setStringPermissions(permission);
        
        // 获取所有的角色
        Set<String> role = new HashSet<>();
        List<RbacRole> roleList = rbacRoleService.listRoleByUserId(rbacUser.getId());
        if (roleList != null) {
            roleList.forEach(r -> {
                if (StringUtils.isNotBlank(r.getName())) {
                    role.add(r.getName());
                }
            });
        }
        info.setRoles(role);
        
        return info;        
    }

    /* (non-Javadoc) 认证 登录
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        char[] passwordC = (char[]) token.getCredentials();
        String password = String.valueOf(passwordC);
        
        RbacUser rbacUser = rbacUserService.getByUsername(username);
        if (null != rbacUser && password.equals(rbacUser.getPassword()) && ValidEnum.VALID.getIsValid().equals(rbacUser.getValid())) {
            return new SimpleAuthenticationInfo(rbacUser.getUsername(), rbacUser.getPassword(), ByteSource.Util.bytes(rbacUser.getSalt()), getName()); // realm
        } else {
            new AuthenticationException("wrong password");
        }
        return null;
    }

}
