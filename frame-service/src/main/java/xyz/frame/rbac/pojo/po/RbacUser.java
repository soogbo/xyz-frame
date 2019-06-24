package xyz.frame.rbac.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 用户信息表
 */
@Entity
@Table(name = RbacUser.TABLE_NAME)
public class RbacUser implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " rbac_user ";
    public static final String COLUMN_LIST = " id,username,password,salt,display_name,phone,email,group_id,valid,locked,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.username,t.password,t.salt,t.display_name,t.phone,t.email,t.group_id,t.valid,t.locked,t.create_at,t.update_at ";        
    
    /**
     * 用户标识
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 用户帐号
     */
    @Id
    @Column(name = "username")
    private String username;
    
    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;
    
    /**
     * 加密密码的盐
     */
    @Column(name = "salt")
    private String salt;
    
    /**
     * 展示名称
     */
    @Column(name = "display_name")
    private String displayName;
    
    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;
    
    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;
    
    /**
     * 分组标识
     */
    @Column(name = "group_id")
    private Long groupId;
    
    /**
     * 1_有效用户 0_无效用户
     */
    @Column(name = "valid")
    private Integer valid;
    
    /**
     * 0_未被锁定 1_锁定
     */
    @Column(name = "locked")
    private Integer locked;
    
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
	 * 设置用户标识
	 * @param id 用户标识
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取用户标识
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置用户帐号
	 * @param username 用户帐号
	 */    
    public void setUsername(String username){
        this.username = username;
    }
    
	/**
	 * 获取用户帐号
	 * @return username
	 */    
    public String getUsername(){
        return this.username;
    }    
    
	/**
	 * 设置用户密码
	 * @param password 用户密码
	 */    
    public void setPassword(String password){
        this.password = password;
    }
    
	/**
	 * 获取用户密码
	 * @return password
	 */    
    public String getPassword(){
        return this.password;
    }    
    
	/**
	 * 设置加密密码的盐
	 * @param salt 加密密码的盐
	 */    
    public void setSalt(String salt){
        this.salt = salt;
    }
    
	/**
	 * 获取加密密码的盐
	 * @return salt
	 */    
    public String getSalt(){
        return this.salt;
    }    
    
	/**
	 * 设置展示名称
	 * @param displayName 展示名称
	 */    
    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }
    
	/**
	 * 获取展示名称
	 * @return displayName
	 */    
    public String getDisplayName(){
        return this.displayName;
    }    
    
	/**
	 * 设置手机号
	 * @param phone 手机号
	 */    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
	/**
	 * 获取手机号
	 * @return phone
	 */    
    public String getPhone(){
        return this.phone;
    }    
    
	/**
	 * 设置邮箱
	 * @param email 邮箱
	 */    
    public void setEmail(String email){
        this.email = email;
    }
    
	/**
	 * 获取邮箱
	 * @return email
	 */    
    public String getEmail(){
        return this.email;
    }    
    
	/**
	 * 设置分组标识
	 * @param groupId 分组标识
	 */    
    public void setGroupId(Long groupId){
        this.groupId = groupId;
    }
    
	/**
	 * 获取分组标识
	 * @return groupId
	 */    
    public Long getGroupId(){
        return this.groupId;
    }    
    
	/**
	 * 设置1_有效用户 0_无效用户
	 * @param valid 1_有效用户 0_无效用户
	 */    
    public void setValid(Integer valid){
        this.valid = valid;
    }
    
	/**
	 * 获取1_有效用户 0_无效用户
	 * @return valid
	 */    
    public Integer getValid(){
        return this.valid;
    }    
    
	/**
	 * 设置0_未被锁定 1_锁定
	 * @param locked 0_未被锁定 1_锁定
	 */    
    public void setLocked(Integer locked){
        this.locked = locked;
    }
    
	/**
	 * 获取0_未被锁定 1_锁定
	 * @return locked
	 */    
    public Integer getLocked(){
        return this.locked;
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