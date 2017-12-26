package xyz.frame.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 参数分类表
 */
@Entity
@Table(name = ParamClass.TABLE_NAME)
public class ParamClass implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "param_class";
    public static final String COLUMN_LIST = "id,class_code,class_name,create_at,update_at";
    public static final String COLUMN_LIST_ALIAS_T = "t.id,t.class_code,t.class_name,t.create_at,t.update_at";        
    
    /**
     * 分类ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 分类编号
     */
    @Column(name = "class_code")
    private String classCode;
    
    /**
     * 分类名称
     */
    @Column(name = "class_name")
    private String className;
    
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
	 * 设置分类ID
	 * @param id 分类ID
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取分类ID
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置分类编号
	 * @param classCode 分类编号
	 */    
    public void setClassCode(String classCode){
        this.classCode = classCode;
    }
    
	/**
	 * 获取分类编号
	 * @return classCode
	 */    
    public String getClassCode(){
        return this.classCode;
    }    
    
	/**
	 * 设置分类名称
	 * @param className 分类名称
	 */    
    public void setClassName(String className){
        this.className = className;
    }
    
	/**
	 * 获取分类名称
	 * @return className
	 */    
    public String getClassName(){
        return this.className;
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