package xyz.frame.rbac.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 角色权限关系表
 */
@Entity
@Table(name = RbacRolePermission.TABLE_NAME)
public class RbacRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " rbac_role_permission ";
    public static final String COLUMN_LIST = " id,role_id,permission_id,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.role_id,t.permission_id,t.create_at,t.update_at ";        
    
    /**
     * 角色权限标识
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 角色权限
     */
    @Column(name = "role_id")
    private Long roleId;
    
    /**
     * 权限标识
     */
    @Column(name = "permission_id")
    private Long permissionId;
    
    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private java.util.Date createAt;
    
    /**
     * 修改时间
     */
    @Column(name = "update_at")
    private java.util.Date updateAt;
    
	/**
	 * 设置角色权限标识
	 * @param id 角色权限标识
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取角色权限标识
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置角色权限
	 * @param roleId 角色权限
	 */    
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }
    
	/**
	 * 获取角色权限
	 * @return roleId
	 */    
    public Long getRoleId(){
        return this.roleId;
    }    
    
	/**
	 * 设置权限标识
	 * @param permissionId 权限标识
	 */    
    public void setPermissionId(Long permissionId){
        this.permissionId = permissionId;
    }
    
	/**
	 * 获取权限标识
	 * @return permissionId
	 */    
    public Long getPermissionId(){
        return this.permissionId;
    }    
    
	/**
	 * 设置创建时间
	 * @param createAt 创建时间
	 */    
    public void setCreateAt(java.util.Date createAt){
        this.createAt = createAt;
    }
    
	/**
	 * 获取创建时间
	 * @return createAt
	 */    
    public java.util.Date getCreateAt(){
        return this.createAt;
    }    
    
	/**
	 * 设置修改时间
	 * @param updateAt 修改时间
	 */    
    public void setUpdateAt(java.util.Date updateAt){
        this.updateAt = updateAt;
    }
    
	/**
	 * 获取修改时间
	 * @return updateAt
	 */    
    public java.util.Date getUpdateAt(){
        return this.updateAt;
    }    
}