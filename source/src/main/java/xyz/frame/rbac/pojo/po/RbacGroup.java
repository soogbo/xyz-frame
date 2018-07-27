package xyz.frame.rbac.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 用户分组表
 */
@Entity
@Table(name = RbacGroup.TABLE_NAME)
public class RbacGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " rbac_group ";
    public static final String COLUMN_LIST = " id,parent_id,parent_ids,name,type,valid,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.parent_id,t.parent_ids,t.name,t.type,t.valid,t.create_at,t.update_at ";        
    
    /**
     * 组标识
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 父组标识
     */
    @Column(name = "parent_id")
    private Long parentId;
    
    /**
     * 父组列表
     */
    @Column(name = "parent_ids")
    private String parentIds;
    
    /**
     * 组的名称
     */
    @Column(name = "name")
    private String name;
    
    /**
     * 组的类型，由具体业务定义
     */
    @Column(name = "type")
    private String type;
    
    /**
     * 是否有效，0_表示无效 1_表示有效
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
	 * 设置组标识
	 * @param id 组标识
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取组标识
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置父组标识
	 * @param parentId 父组标识
	 */    
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    
	/**
	 * 获取父组标识
	 * @return parentId
	 */    
    public Long getParentId(){
        return this.parentId;
    }    
    
	/**
	 * 设置父组列表
	 * @param parentIds 父组列表
	 */    
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
    
	/**
	 * 获取父组列表
	 * @return parentIds
	 */    
    public String getParentIds(){
        return this.parentIds;
    }    
    
	/**
	 * 设置组的名称
	 * @param name 组的名称
	 */    
    public void setName(String name){
        this.name = name;
    }
    
	/**
	 * 获取组的名称
	 * @return name
	 */    
    public String getName(){
        return this.name;
    }    
    
	/**
	 * 设置组的类型，由具体业务定义
	 * @param type 组的类型，由具体业务定义
	 */    
    public void setType(String type){
        this.type = type;
    }
    
	/**
	 * 获取组的类型，由具体业务定义
	 * @return type
	 */    
    public String getType(){
        return this.type;
    }    
    
	/**
	 * 设置是否有效，0_表示无效 1_表示有效
	 * @param valid 是否有效，0_表示无效 1_表示有效
	 */    
    public void setValid(Integer valid){
        this.valid = valid;
    }
    
	/**
	 * 获取是否有效，0_表示无效 1_表示有效
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