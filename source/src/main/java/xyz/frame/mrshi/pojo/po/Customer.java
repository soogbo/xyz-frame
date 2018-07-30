package xyz.frame.mrshi.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 客户表
 */
@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " customer ";
    public static final String COLUMN_LIST = " id,name,byname,group_id,phone,phone_location,phone_new,province,city,county,town,address,address_new,identity_card,birth_date,age,gender,identity_image,identity_image_back,farmland,photo,photo_new,status,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.name,t.byname,t.group_id,t.phone,t.phone_location,t.phone_new,t.province,t.city,t.county,t.town,t.address,t.address_new,t.identity_card,t.birth_date,t.age,t.gender,t.identity_image,t.identity_image_back,t.farmland,t.photo,t.photo_new,t.status,t.create_at,t.update_at ";        
    
    /**
     * 客户id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 客户姓名
     */
    @Column(name = "name")
    private String name;
    
    /**
     * 别名
     */
    @Column(name = "byname")
    private String byname;
    
    /**
     * 所属组id
     */
    @Column(name = "group_id")
    private Long groupId;
    
    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;
    
    /**
     * 手机号归属地
     */
    @Column(name = "phone_location")
    private String phoneLocation;
    
    /**
     * 新手机号
     */
    @Column(name = "phone_new")
    private String phoneNew;
    
    /**
     * 省
     */
    @Column(name = "province")
    private String province;
    
    /**
     * 市
     */
    @Column(name = "city")
    private String city;
    
    /**
     * 县
     */
    @Column(name = "county")
    private String county;
    
    /**
     * 乡镇
     */
    @Column(name = "town")
    private String town;
    
    /**
     * 地址明细
     */
    @Column(name = "address")
    private String address;
    
    /**
     * 新_地址明细
     */
    @Column(name = "address_new")
    private String addressNew;
    
    /**
     * 身份证号
     */
    @Column(name = "identity_card")
    private String identityCard;
    
    /**
     * 出生日期：yyyy-MM-dd
     */
    @Column(name = "birth_date")
    private String birthDate;
    
    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;
    
    /**
     * 性别
     */
    @Column(name = "gender")
    private Integer gender;
    
    /**
     * 身份证照片
     */
    @Column(name = "identity_image")
    private String identityImage;
    
    /**
     * 身份证照片back
     */
    @Column(name = "identity_image_back")
    private String identityImageBack;
    
    /**
     * 亩地/分
     */
    @Column(name = "farmland")
    private Integer farmland;
    
    /**
     * 照片
     */
    @Column(name = "photo")
    private String photo;
    
    /**
     * 新_照片
     */
    @Column(name = "photo_new")
    private String photoNew;
    
    /**
     * 可用状态：1-可用，0-不可用
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
	 * 设置客户id
	 * @param id 客户id
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取客户id
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置客户姓名
	 * @param name 客户姓名
	 */    
    public void setName(String name){
        this.name = name;
    }
    
	/**
	 * 获取客户姓名
	 * @return name
	 */    
    public String getName(){
        return this.name;
    }    
    
	/**
	 * 设置别名
	 * @param byname 别名
	 */    
    public void setByname(String byname){
        this.byname = byname;
    }
    
	/**
	 * 获取别名
	 * @return byname
	 */    
    public String getByname(){
        return this.byname;
    }    
    
	/**
	 * 设置所属组id
	 * @param groupId 所属组id
	 */    
    public void setGroupId(Long groupId){
        this.groupId = groupId;
    }
    
	/**
	 * 获取所属组id
	 * @return groupId
	 */    
    public Long getGroupId(){
        return this.groupId;
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
	 * 设置手机号归属地
	 * @param phoneLocation 手机号归属地
	 */    
    public void setPhoneLocation(String phoneLocation){
        this.phoneLocation = phoneLocation;
    }
    
	/**
	 * 获取手机号归属地
	 * @return phoneLocation
	 */    
    public String getPhoneLocation(){
        return this.phoneLocation;
    }    
    
	/**
	 * 设置新手机号
	 * @param phoneNew 新手机号
	 */    
    public void setPhoneNew(String phoneNew){
        this.phoneNew = phoneNew;
    }
    
	/**
	 * 获取新手机号
	 * @return phoneNew
	 */    
    public String getPhoneNew(){
        return this.phoneNew;
    }    
    
	/**
	 * 设置省
	 * @param province 省
	 */    
    public void setProvince(String province){
        this.province = province;
    }
    
	/**
	 * 获取省
	 * @return province
	 */    
    public String getProvince(){
        return this.province;
    }    
    
	/**
	 * 设置市
	 * @param city 市
	 */    
    public void setCity(String city){
        this.city = city;
    }
    
	/**
	 * 获取市
	 * @return city
	 */    
    public String getCity(){
        return this.city;
    }    
    
	/**
	 * 设置县
	 * @param county 县
	 */    
    public void setCounty(String county){
        this.county = county;
    }
    
	/**
	 * 获取县
	 * @return county
	 */    
    public String getCounty(){
        return this.county;
    }    
    
	/**
	 * 设置乡镇
	 * @param town 乡镇
	 */    
    public void setTown(String town){
        this.town = town;
    }
    
	/**
	 * 获取乡镇
	 * @return town
	 */    
    public String getTown(){
        return this.town;
    }    
    
	/**
	 * 设置地址明细
	 * @param address 地址明细
	 */    
    public void setAddress(String address){
        this.address = address;
    }
    
	/**
	 * 获取地址明细
	 * @return address
	 */    
    public String getAddress(){
        return this.address;
    }    
    
	/**
	 * 设置新_地址明细
	 * @param addressNew 新_地址明细
	 */    
    public void setAddressNew(String addressNew){
        this.addressNew = addressNew;
    }
    
	/**
	 * 获取新_地址明细
	 * @return addressNew
	 */    
    public String getAddressNew(){
        return this.addressNew;
    }    
    
	/**
	 * 设置身份证号
	 * @param identityCard 身份证号
	 */    
    public void setIdentityCard(String identityCard){
        this.identityCard = identityCard;
    }
    
	/**
	 * 获取身份证号
	 * @return identityCard
	 */    
    public String getIdentityCard(){
        return this.identityCard;
    }    
    
	/**
	 * 设置出生日期：yyyy-MM-dd
	 * @param birthDate 出生日期：yyyy-MM-dd
	 */    
    public void setBirthDate(String birthDate){
        this.birthDate = birthDate;
    }
    
	/**
	 * 获取出生日期：yyyy-MM-dd
	 * @return birthDate
	 */    
    public String getBirthDate(){
        return this.birthDate;
    }    
    
	/**
	 * 设置年龄
	 * @param age 年龄
	 */    
    public void setAge(Integer age){
        this.age = age;
    }
    
	/**
	 * 获取年龄
	 * @return age
	 */    
    public Integer getAge(){
        return this.age;
    }    
    
	/**
	 * 设置性别
	 * @param gender 性别
	 */    
    public void setGender(Integer gender){
        this.gender = gender;
    }
    
	/**
	 * 获取性别
	 * @return gender
	 */    
    public Integer getGender(){
        return this.gender;
    }    
    
	/**
	 * 设置身份证照片
	 * @param identityImage 身份证照片
	 */    
    public void setIdentityImage(String identityImage){
        this.identityImage = identityImage;
    }
    
	/**
	 * 获取身份证照片
	 * @return identityImage
	 */    
    public String getIdentityImage(){
        return this.identityImage;
    }    
    
	/**
	 * 设置身份证照片back
	 * @param identityImageBack 身份证照片back
	 */    
    public void setIdentityImageBack(String identityImageBack){
        this.identityImageBack = identityImageBack;
    }
    
	/**
	 * 获取身份证照片back
	 * @return identityImageBack
	 */    
    public String getIdentityImageBack(){
        return this.identityImageBack;
    }    
    
	/**
	 * 设置亩地/分
	 * @param farmland 亩地/分
	 */    
    public void setFarmland(Integer farmland){
        this.farmland = farmland;
    }
    
	/**
	 * 获取亩地/分
	 * @return farmland
	 */    
    public Integer getFarmland(){
        return this.farmland;
    }    
    
	/**
	 * 设置照片
	 * @param photo 照片
	 */    
    public void setPhoto(String photo){
        this.photo = photo;
    }
    
	/**
	 * 获取照片
	 * @return photo
	 */    
    public String getPhoto(){
        return this.photo;
    }    
    
	/**
	 * 设置新_照片
	 * @param photoNew 新_照片
	 */    
    public void setPhotoNew(String photoNew){
        this.photoNew = photoNew;
    }
    
	/**
	 * 获取新_照片
	 * @return photoNew
	 */    
    public String getPhotoNew(){
        return this.photoNew;
    }    
    
	/**
	 * 设置可用状态：1-可用，0-不可用
	 * @param status 可用状态：1-可用，0-不可用
	 */    
    public void setStatus(Integer status){
        this.status = status;
    }
    
	/**
	 * 获取可用状态：1-可用，0-不可用
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