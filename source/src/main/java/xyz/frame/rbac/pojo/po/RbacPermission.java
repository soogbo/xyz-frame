package xyz.frame.rbac.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 权限表
 */
@Entity
@Table(name = RbacPermission.TABLE_NAME)
public class RbacPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " rbac_permission ";
    public static final String COLUMN_LIST = " id,parent_id,name,permission,anonymous,valid,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.parent_id,t.name,t.permission,t.anonymous,t.valid,t.create_at,t.update_at ";        
    
    /**
     * 权限标识
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 父权限标识
     */
    @Column(name = "parent_id")
    private Long parentId;
    
    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;
    
    /**
     * 权限字符串，多个以逗号分隔。例：服务名1.方法名1,服务名2.方法名2
     */
    @Column(name = "permission")
    private String permission;
    
    /**
     * 0_不允许匿名访问,1_允许匿名访问
     */
    @Column(name = "anonymous")
    private Integer anonymous;
    
    /**
     * 0_表示无效 1_表示有效
     */
    @Column(name = "valid")
    private Integer valid;
    
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
	 * 设置权限标识
	 * @param id 权限标识
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取权限标识
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置父权限标识
	 * @param parentId 父权限标识
	 */    
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    
	/**
	 * 获取父权限标识
	 * @return parentId
	 */    
    public Long getParentId(){
        return this.parentId;
    }    
    
	/**
	 * 设置权限名称
	 * @param name 权限名称
	 */    
    public void setName(String name){
        this.name = name;
    }
    
	/**
	 * 获取权限名称
	 * @return name
	 */    
    public String getName(){
        return this.name;
    }    
    
	/**
	 * 设置权限字符串，多个以逗号分隔。例：服务名1.方法名1,服务名2.方法名2
	 * @param permission 权限字符串，多个以逗号分隔。例：服务名1.方法名1,服务名2.方法名2
	 */    
    public void setPermission(String permission){
        this.permission = permission;
    }
    
	/**
	 * 获取权限字符串，多个以逗号分隔。例：服务名1.方法名1,服务名2.方法名2
	 * @return permission
	 */    
    public String getPermission(){
        return this.permission;
    }    
    
	/**
	 * 设置0_不允许匿名访问,1_允许匿名访问
	 * @param anonymous 0_不允许匿名访问,1_允许匿名访问
	 */    
    public void setAnonymous(Integer anonymous){
        this.anonymous = anonymous;
    }
    
	/**
	 * 获取0_不允许匿名访问,1_允许匿名访问
	 * @return anonymous
	 */    
    public Integer getAnonymous(){
        return this.anonymous;
    }    
    
	/**
	 * 设置0_表示无效 1_表示有效
	 * @param valid 0_表示无效 1_表示有效
	 */    
    public void setValid(Integer valid){
        this.valid = valid;
    }
    
	/**
	 * 获取0_表示无效 1_表示有效
	 * @return valid
	 */    
    public Integer getValid(){
        return this.valid;
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