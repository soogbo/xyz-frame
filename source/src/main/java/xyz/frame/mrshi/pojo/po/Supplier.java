package xyz.frame.mrshi.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 供应商表
 */
@Entity
@Table(name = Supplier.TABLE_NAME)
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " supplier ";
    public static final String COLUMN_LIST = " id,name,address,contact,phone,image,contact2,phone2,image2,status,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.name,t.address,t.contact,t.phone,t.image,t.contact2,t.phone2,t.image2,t.status,t.create_at,t.update_at ";        
    
    /**
     * 供应商id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 名称
     */
    @Column(name = "name")
    private String name;
    
    /**
     * 地址
     */
    @Column(name = "address")
    private String address;
    
    /**
     * 联系人
     */
    @Column(name = "contact")
    private String contact;
    
    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;
    
    /**
     * 照片
     */
    @Column(name = "image")
    private String image;
    
    /**
     * 联系人2
     */
    @Column(name = "contact2")
    private String contact2;
    
    /**
     * 手机号2
     */
    @Column(name = "phone2")
    private String phone2;
    
    /**
     * 照片2
     */
    @Column(name = "image2")
    private String image2;
    
    /**
     * 状态：0-不可用，1-供应商，2-经销商
     */
    @Column(name = "status")
    private Integer status;
    
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
	 * 设置供应商id
	 * @param id 供应商id
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取供应商id
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置名称
	 * @param name 名称
	 */    
    public void setName(String name){
        this.name = name;
    }
    
	/**
	 * 获取名称
	 * @return name
	 */    
    public String getName(){
        return this.name;
    }    
    
	/**
	 * 设置地址
	 * @param address 地址
	 */    
    public void setAddress(String address){
        this.address = address;
    }
    
	/**
	 * 获取地址
	 * @return address
	 */    
    public String getAddress(){
        return this.address;
    }    
    
	/**
	 * 设置联系人
	 * @param contact 联系人
	 */    
    public void setContact(String contact){
        this.contact = contact;
    }
    
	/**
	 * 获取联系人
	 * @return contact
	 */    
    public String getContact(){
        return this.contact;
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
	 * 设置照片
	 * @param image 照片
	 */    
    public void setImage(String image){
        this.image = image;
    }
    
	/**
	 * 获取照片
	 * @return image
	 */    
    public String getImage(){
        return this.image;
    }    
    
	/**
	 * 设置联系人2
	 * @param contact2 联系人2
	 */    
    public void setContact2(String contact2){
        this.contact2 = contact2;
    }
    
	/**
	 * 获取联系人2
	 * @return contact2
	 */    
    public String getContact2(){
        return this.contact2;
    }    
    
	/**
	 * 设置手机号2
	 * @param phone2 手机号2
	 */    
    public void setPhone2(String phone2){
        this.phone2 = phone2;
    }
    
	/**
	 * 获取手机号2
	 * @return phone2
	 */    
    public String getPhone2(){
        return this.phone2;
    }    
    
	/**
	 * 设置照片2
	 * @param image2 照片2
	 */    
    public void setImage2(String image2){
        this.image2 = image2;
    }
    
	/**
	 * 获取照片2
	 * @return image2
	 */    
    public String getImage2(){
        return this.image2;
    }    
    
	/**
	 * 设置状态：0-不可用，1-供应商，2-经销商
	 * @param status 状态：0-不可用，1-供应商，2-经销商
	 */    
    public void setStatus(Integer status){
        this.status = status;
    }
    
	/**
	 * 获取状态：0-不可用，1-供应商，2-经销商
	 * @return status
	 */    
    public Integer getStatus(){
        return this.status;
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