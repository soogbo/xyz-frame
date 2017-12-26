package xyz.frame.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 系统参数表
 */
@Entity
@Table(name = ParamParam.TABLE_NAME)
public class ParamParam implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "param_param";
    public static final String COLUMN_LIST = "id,parent_id,class_id,param_code,param_name,param_value,param_extone,param_exttwo,param_extthree,create_at,update_at";
    public static final String COLUMN_LIST_ALIAS_T = "t.id,t.parent_id,t.class_id,t.param_code,t.param_name,t.param_value,t.param_extone,t.param_exttwo,t.param_extthree,t.create_at,t.update_at";        
    
    /**
     * 参数ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 父参数ID
     */
    @Column(name = "parent_id")
    private Long parentId;
    
    /**
     * 分类ID
     */
    @Column(name = "class_id")
    private Long classId;
    
    /**
     * 参数码
     */
    @Column(name = "param_code")
    private String paramCode;
    
    /**
     * 参数名
     */
    @Column(name = "param_name")
    private String paramName;
    
    /**
     * 参数值
     */
    @Column(name = "param_value")
    private String paramValue;
    
    /**
     * 扩展字段1
     */
    @Column(name = "param_extone")
    private String paramExtone;
    
    /**
     * 扩展字段2
     */
    @Column(name = "param_exttwo")
    private String paramExttwo;
    
    /**
     * 扩展字段3
     */
    @Column(name = "param_extthree")
    private String paramExtthree;
    
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
	 * 设置参数ID
	 * @param id 参数ID
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取参数ID
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置父参数ID
	 * @param parentId 父参数ID
	 */    
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    
	/**
	 * 获取父参数ID
	 * @return parentId
	 */    
    public Long getParentId(){
        return this.parentId;
    }    
    
	/**
	 * 设置分类ID
	 * @param classId 分类ID
	 */    
    public void setClassId(Long classId){
        this.classId = classId;
    }
    
	/**
	 * 获取分类ID
	 * @return classId
	 */    
    public Long getClassId(){
        return this.classId;
    }    
    
	/**
	 * 设置参数码
	 * @param paramCode 参数码
	 */    
    public void setParamCode(String paramCode){
        this.paramCode = paramCode;
    }
    
	/**
	 * 获取参数码
	 * @return paramCode
	 */    
    public String getParamCode(){
        return this.paramCode;
    }    
    
	/**
	 * 设置参数名
	 * @param paramName 参数名
	 */    
    public void setParamName(String paramName){
        this.paramName = paramName;
    }
    
	/**
	 * 获取参数名
	 * @return paramName
	 */    
    public String getParamName(){
        return this.paramName;
    }    
    
	/**
	 * 设置参数值
	 * @param paramValue 参数值
	 */    
    public void setParamValue(String paramValue){
        this.paramValue = paramValue;
    }
    
	/**
	 * 获取参数值
	 * @return paramValue
	 */    
    public String getParamValue(){
        return this.paramValue;
    }    
    
	/**
	 * 设置扩展字段1
	 * @param paramExtone 扩展字段1
	 */    
    public void setParamExtone(String paramExtone){
        this.paramExtone = paramExtone;
    }
    
	/**
	 * 获取扩展字段1
	 * @return paramExtone
	 */    
    public String getParamExtone(){
        return this.paramExtone;
    }    
    
	/**
	 * 设置扩展字段2
	 * @param paramExttwo 扩展字段2
	 */    
    public void setParamExttwo(String paramExttwo){
        this.paramExttwo = paramExttwo;
    }
    
	/**
	 * 获取扩展字段2
	 * @return paramExttwo
	 */    
    public String getParamExttwo(){
        return this.paramExttwo;
    }    
    
	/**
	 * 设置扩展字段3
	 * @param paramExtthree 扩展字段3
	 */    
    public void setParamExtthree(String paramExtthree){
        this.paramExtthree = paramExtthree;
    }
    
	/**
	 * 获取扩展字段3
	 * @return paramExtthree
	 */    
    public String getParamExtthree(){
        return this.paramExtthree;
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