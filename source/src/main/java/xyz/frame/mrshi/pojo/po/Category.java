package xyz.frame.mrshi.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 商品类型表
 */
@Entity
@Table(name = Category.TABLE_NAME)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " category ";
    public static final String COLUMN_LIST = " id,name,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.name,t.create_at,t.update_at ";        
    
    /**
     * 类型id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 类型名称
     */
    @Column(name = "name")
    private String name;
    
    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private java.util.Date createAt;
    
    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private java.util.Date updateAt;
    
	/**
	 * 设置类型id
	 * @param id 类型id
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取类型id
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置类型名称
	 * @param name 类型名称
	 */    
    public void setName(String name){
        this.name = name;
    }
    
	/**
	 * 获取类型名称
	 * @return name
	 */    
    public String getName(){
        return this.name;
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
	 * 设置更新时间
	 * @param updateAt 更新时间
	 */    
    public void setUpdateAt(java.util.Date updateAt){
        this.updateAt = updateAt;
    }
    
	/**
	 * 获取更新时间
	 * @return updateAt
	 */    
    public java.util.Date getUpdateAt(){
        return this.updateAt;
    }    
}