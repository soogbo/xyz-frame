package xyz.frame.mrshi.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 订单商品表
 */
@Entity
@Table(name = OrderGoods.TABLE_NAME)
public class OrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " order_goods ";
    public static final String COLUMN_LIST = " id,order_id,goods_id,goods_num,goods_price,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.order_id,t.goods_id,t.goods_num,t.goods_price,t.create_at,t.update_at ";        
    
    /**
     * 订单商品id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;
    
    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Long goodsId;
    
    /**
     * 商品数量
     */
    @Column(name = "goods_num")
    private Integer goodsNum;
    
    /**
     * 商品总价
     */
    @Column(name = "goods_price")
    private Integer goodsPrice;
    
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
	 * 设置订单商品id
	 * @param id 订单商品id
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取订单商品id
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置订单id
	 * @param orderId 订单id
	 */    
    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }
    
	/**
	 * 获取订单id
	 * @return orderId
	 */    
    public Long getOrderId(){
        return this.orderId;
    }    
    
	/**
	 * 设置商品id
	 * @param goodsId 商品id
	 */    
    public void setGoodsId(Long goodsId){
        this.goodsId = goodsId;
    }
    
	/**
	 * 获取商品id
	 * @return goodsId
	 */    
    public Long getGoodsId(){
        return this.goodsId;
    }    
    
	/**
	 * 设置商品数量
	 * @param goodsNum 商品数量
	 */    
    public void setGoodsNum(Integer goodsNum){
        this.goodsNum = goodsNum;
    }
    
	/**
	 * 获取商品数量
	 * @return goodsNum
	 */    
    public Integer getGoodsNum(){
        return this.goodsNum;
    }    
    
	/**
	 * 设置商品总价
	 * @param goodsPrice 商品总价
	 */    
    public void setGoodsPrice(Integer goodsPrice){
        this.goodsPrice = goodsPrice;
    }
    
	/**
	 * 获取商品总价
	 * @return goodsPrice
	 */    
    public Integer getGoodsPrice(){
        return this.goodsPrice;
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