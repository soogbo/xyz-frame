package xyz.frame.rbac.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 用户角色关系表
 */
@Entity
@Table(name = RbacUserRole.TABLE_NAME)
public class RbacUserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " rbac_user_role ";
    public static final String COLUMN_LIST = " id,user_id,role_id,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.user_id,t.role_id,t.create_at,t.update_at ";        
    
    /**
     * 用户角色标识
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 用户标识
     */
    @Column(name = "user_id")
    private Long userId;
    
    /**
     * 角色标识
     */
    @Column(name = "role_id")
    private Long roleId;
    
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
	 * 设置用户角色标识
	 * @param id 用户角色标识
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取用户角色标识
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置用户标识
	 * @param userId 用户标识
	 */    
    public void setUserId(Long userId){
        this.userId = userId;
    }
    
	/**
	 * 获取用户标识
	 * @return userId
	 */    
    public Long getUserId(){
        return this.userId;
    }    
    
	/**
	 * 设置角色标识
	 * @param roleId 角色标识
	 */    
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }
    
	/**
	 * 获取角色标识
	 * @return roleId
	 */    
    public Long getRoleId(){
        return this.roleId;
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