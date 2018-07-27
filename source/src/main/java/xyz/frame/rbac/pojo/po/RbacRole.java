package xyz.frame.rbac.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 角色表
 */
@Entity
@Table(name = RbacRole.TABLE_NAME)
public class RbacRole implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " rbac_role ";
    public static final String COLUMN_LIST = " id,name,description,valid,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.name,t.description,t.valid,t.create_at,t.update_at ";        
    
    /**
     * 角色标识
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;
    
    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;
    
    /**
     * 0_无效角色 1_有效角色
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
	 * 设置角色标识
	 * @param id 角色标识
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取角色标识
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置角色名称
	 * @param name 角色名称
	 */    
    public void setName(String name){
        this.name = name;
    }
    
	/**
	 * 获取角色名称
	 * @return name
	 */    
    public String getName(){
        return this.name;
    }    
    
	/**
	 * 设置角色描述
	 * @param description 角色描述
	 */    
    public void setDescription(String description){
        this.description = description;
    }
    
	/**
	 * 获取角色描述
	 * @return description
	 */    
    public String getDescription(){
        return this.description;
    }    
    
	/**
	 * 设置0_无效角色 1_有效角色
	 * @param valid 0_无效角色 1_有效角色
	 */    
    public void setValid(Integer valid){
        this.valid = valid;
    }
    
	/**
	 * 获取0_无效角色 1_有效角色
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