package xyz.frame.mrshi.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 商品表
 */
@Entity
@Table(name = Goods.TABLE_NAME)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " goods ";
    public static final String COLUMN_LIST = " id,name,goods_no,price,price_new,supplier_id,total_stock,stock_balance,category_id,goods_desc,image,image_new,status,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.name,t.goods_no,t.price,t.price_new,t.supplier_id,t.total_stock,t.stock_balance,t.category_id,t.goods_desc,t.image,t.image_new,t.status,t.create_at,t.update_at ";        
    
    /**
     * 商品id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;
    
    /**
     * 商品编号
     */
    @Column(name = "goods_no")
    private String goodsNo;
    
    /**
     * 价格
     */
    @Column(name = "price")
    private Integer price;
    
    /**
     * 新_价格
     */
    @Column(name = "price_new")
    private Integer priceNew;
    
    /**
     * 供应商id
     */
    @Column(name = "supplier_id")
    private Long supplierId;
    
    /**
     * 总库存
     */
    @Column(name = "total_stock")
    private Integer totalStock;
    
    /**
     * 剩余库存
     */
    @Column(name = "stock_balance")
    private Integer stockBalance;
    
    /**
     * 类别id
     */
    @Column(name = "category_id")
    private Long categoryId;
    
    /**
     * 描述
     */
    @Column(name = "goods_desc")
    private String goodsDesc;
    
    /**
     * 图片
     */
    @Column(name = "image")
    private String image;
    
    /**
     * 新_图片
     */
    @Column(name = "image_new")
    private String imageNew;
    
    /**
     * 是否可用
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
	 * 设置商品id
	 * @param id 商品id
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取商品id
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置商品名称
	 * @param name 商品名称
	 */    
    public void setName(String name){
        this.name = name;
    }
    
	/**
	 * 获取商品名称
	 * @return name
	 */    
    public String getName(){
        return this.name;
    }    
    
	/**
	 * 设置商品编号
	 * @param goodsNo 商品编号
	 */    
    public void setGoodsNo(String goodsNo){
        this.goodsNo = goodsNo;
    }
    
	/**
	 * 获取商品编号
	 * @return goodsNo
	 */    
    public String getGoodsNo(){
        return this.goodsNo;
    }    
    
	/**
	 * 设置价格
	 * @param price 价格
	 */    
    public void setPrice(Integer price){
        this.price = price;
    }
    
	/**
	 * 获取价格
	 * @return price
	 */    
    public Integer getPrice(){
        return this.price;
    }    
    
	/**
	 * 设置新_价格
	 * @param priceNew 新_价格
	 */    
    public void setPriceNew(Integer priceNew){
        this.priceNew = priceNew;
    }
    
	/**
	 * 获取新_价格
	 * @return priceNew
	 */    
    public Integer getPriceNew(){
        return this.priceNew;
    }    
    
	/**
	 * 设置供应商id
	 * @param supplierId 供应商id
	 */    
    public void setSupplierId(Long supplierId){
        this.supplierId = supplierId;
    }
    
	/**
	 * 获取供应商id
	 * @return supplierId
	 */    
    public Long getSupplierId(){
        return this.supplierId;
    }    
    
	/**
	 * 设置总库存
	 * @param totalStock 总库存
	 */    
    public void setTotalStock(Integer totalStock){
        this.totalStock = totalStock;
    }
    
	/**
	 * 获取总库存
	 * @return totalStock
	 */    
    public Integer getTotalStock(){
        return this.totalStock;
    }    
    
	/**
	 * 设置剩余库存
	 * @param stockBalance 剩余库存
	 */    
    public void setStockBalance(Integer stockBalance){
        this.stockBalance = stockBalance;
    }
    
	/**
	 * 获取剩余库存
	 * @return stockBalance
	 */    
    public Integer getStockBalance(){
        return this.stockBalance;
    }    
    
	/**
	 * 设置类别id
	 * @param categoryId 类别id
	 */    
    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
    
	/**
	 * 获取类别id
	 * @return categoryId
	 */    
    public Long getCategoryId(){
        return this.categoryId;
    }    
    
	/**
	 * 设置描述
	 * @param goodsDesc 描述
	 */    
    public void setGoodsDesc(String goodsDesc){
        this.goodsDesc = goodsDesc;
    }
    
	/**
	 * 获取描述
	 * @return goodsDesc
	 */    
    public String getGoodsDesc(){
        return this.goodsDesc;
    }    
    
	/**
	 * 设置图片
	 * @param image 图片
	 */    
    public void setImage(String image){
        this.image = image;
    }
    
	/**
	 * 获取图片
	 * @return image
	 */    
    public String getImage(){
        return this.image;
    }    
    
	/**
	 * 设置新_图片
	 * @param imageNew 新_图片
	 */    
    public void setImageNew(String imageNew){
        this.imageNew = imageNew;
    }
    
	/**
	 * 获取新_图片
	 * @return imageNew
	 */    
    public String getImageNew(){
        return this.imageNew;
    }    
    
	/**
	 * 设置是否可用
	 * @param status 是否可用
	 */    
    public void setStatus(Integer status){
        this.status = status;
    }
    
	/**
	 * 获取是否可用
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