package xyz.frame.rbac.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 菜单表
 */
@Entity
@Table(name = RbacMenu.TABLE_NAME)
public class RbacMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " rbac_menu ";
    public static final String COLUMN_LIST = " id,parent_id,permission,path,name,description,sort,valid,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.parent_id,t.permission,t.path,t.name,t.description,t.sort,t.valid,t.create_at,t.update_at ";        
    
    /**
     * 菜单标识
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 父菜单标识
     */
    @Column(name = "parent_id")
    private Long parentId;
    
    /**
     * 权限字符串
     */
    @Column(name = "permission")
    private String permission;
    
    /**
     * 前端路由信息
     */
    @Column(name = "path")
    private String path;
    
    /**
     * 菜单名称
     */
    @Column(name = "name")
    private String name;
    
    /**
     * 菜单描述
     */
    @Column(name = "description")
    private String description;
    
    /**
     * 展示的顺序
     */
    @Column(name = "sort")
    private Integer sort;
    
    /**
     * 0_无效菜单 1_有效菜单
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
	 * 设置菜单标识
	 * @param id 菜单标识
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取菜单标识
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置父菜单标识
	 * @param parentId 父菜单标识
	 */    
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    
	/**
	 * 获取父菜单标识
	 * @return parentId
	 */    
    public Long getParentId(){
        return this.parentId;
    }    
    
	/**
	 * 设置权限字符串
	 * @param permission 权限字符串
	 */    
    public void setPermission(String permission){
        this.permission = permission;
    }
    
	/**
	 * 获取权限字符串
	 * @return permission
	 */    
    public String getPermission(){
        return this.permission;
    }    
    
	/**
	 * 设置前端路由信息
	 * @param path 前端路由信息
	 */    
    public void setPath(String path){
        this.path = path;
    }
    
	/**
	 * 获取前端路由信息
	 * @return path
	 */    
    public String getPath(){
        return this.path;
    }    
    
	/**
	 * 设置菜单名称
	 * @param name 菜单名称
	 */    
    public void setName(String name){
        this.name = name;
    }
    
	/**
	 * 获取菜单名称
	 * @return name
	 */    
    public String getName(){
        return this.name;
    }    
    
	/**
	 * 设置菜单描述
	 * @param description 菜单描述
	 */    
    public void setDescription(String description){
        this.description = description;
    }
    
	/**
	 * 获取菜单描述
	 * @return description
	 */    
    public String getDescription(){
        return this.description;
    }    
    
	/**
	 * 设置展示的顺序
	 * @param sort 展示的顺序
	 */    
    public void setSort(Integer sort){
        this.sort = sort;
    }
    
	/**
	 * 获取展示的顺序
	 * @return sort
	 */    
    public Integer getSort(){
        return this.sort;
    }    
    
	/**
	 * 设置0_无效菜单 1_有效菜单
	 * @param valid 0_无效菜单 1_有效菜单
	 */    
    public void setValid(Integer valid){
        this.valid = valid;
    }
    
	/**
	 * 获取0_无效菜单 1_有效菜单
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