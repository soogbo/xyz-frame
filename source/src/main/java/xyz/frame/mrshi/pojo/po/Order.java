package xyz.frame.mrshi.pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 订单表
 */
@Entity
@Table(name = Order.TABLE_NAME)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " order ";
    public static final String COLUMN_LIST = " id,customer_id,order_no,total_price,total_goods_num,has_pay,pay_price,pay_at,has_ship,ship_num,ship_at,status,create_at,update_at ";
    public static final String COLUMN_LIST_ALIAS_T = " t.id,t.customer_id,t.order_no,t.total_price,t.total_goods_num,t.has_pay,t.pay_price,t.pay_at,t.has_ship,t.ship_num,t.ship_at,t.status,t.create_at,t.update_at ";        
    
    /**
     * 订单id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 客户id
     */
    @Column(name = "customer_id")
    private Long customerId;
    
    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;
    
    /**
     * 总金额/分
     */
    @Column(name = "total_price")
    private Long totalPrice;
    
    /**
     * 商品总数
     */
    @Column(name = "total_goods_num")
    private Integer totalGoodsNum;
    
    /**
     * 是否付款
     */
    @Column(name = "has_pay")
    private Integer hasPay;
    
    /**
     * 付款金额/分
     */
    @Column(name = "pay_price")
    private Integer payPrice;
    
    /**
     * 付款时间
     */
    @Column(name = "pay_at")
    private java.util.Date payAt;
    
    /**
     * 是否发货
     */
    @Column(name = "has_ship")
    private Integer hasShip;
    
    /**
     * 发货单号
     */
    @Column(name = "ship_num")
    private String shipNum;
    
    /**
     * 发货时间
     */
    @Column(name = "ship_at")
    private java.util.Date shipAt;
    
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
	 * 设置订单id
	 * @param id 订单id
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取订单id
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置客户id
	 * @param customerId 客户id
	 */    
    public void setCustomerId(Long customerId){
        this.customerId = customerId;
    }
    
	/**
	 * 获取客户id
	 * @return customerId
	 */    
    public Long getCustomerId(){
        return this.customerId;
    }    
    
	/**
	 * 设置订单号
	 * @param orderNo 订单号
	 */    
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }
    
	/**
	 * 获取订单号
	 * @return orderNo
	 */    
    public String getOrderNo(){
        return this.orderNo;
    }    
    
	/**
	 * 设置总金额/分
	 * @param totalPrice 总金额/分
	 */    
    public void setTotalPrice(Long totalPrice){
        this.totalPrice = totalPrice;
    }
    
	/**
	 * 获取总金额/分
	 * @return totalPrice
	 */    
    public Long getTotalPrice(){
        return this.totalPrice;
    }    
    
	/**
	 * 设置商品总数
	 * @param totalGoodsNum 商品总数
	 */    
    public void setTotalGoodsNum(Integer totalGoodsNum){
        this.totalGoodsNum = totalGoodsNum;
    }
    
	/**
	 * 获取商品总数
	 * @return totalGoodsNum
	 */    
    public Integer getTotalGoodsNum(){
        return this.totalGoodsNum;
    }    
    
	/**
	 * 设置是否付款
	 * @param hasPay 是否付款
	 */    
    public void setHasPay(Integer hasPay){
        this.hasPay = hasPay;
    }
    
	/**
	 * 获取是否付款
	 * @return hasPay
	 */    
    public Integer getHasPay(){
        return this.hasPay;
    }    
    
	/**
	 * 设置付款金额/分
	 * @param payPrice 付款金额/分
	 */    
    public void setPayPrice(Integer payPrice){
        this.payPrice = payPrice;
    }
    
	/**
	 * 获取付款金额/分
	 * @return payPrice
	 */    
    public Integer getPayPrice(){
        return this.payPrice;
    }    
    
	/**
	 * 设置付款时间
	 * @param payAt 付款时间
	 */    
    public void setPayAt(java.util.Date payAt){
        this.payAt = payAt;
    }
    
	/**
	 * 获取付款时间
	 * @return payAt
	 */    
    public java.util.Date getPayAt(){
        return this.payAt;
    }    
    
	/**
	 * 设置是否发货
	 * @param hasShip 是否发货
	 */    
    public void setHasShip(Integer hasShip){
        this.hasShip = hasShip;
    }
    
	/**
	 * 获取是否发货
	 * @return hasShip
	 */    
    public Integer getHasShip(){
        return this.hasShip;
    }    
    
	/**
	 * 设置发货单号
	 * @param shipNum 发货单号
	 */    
    public void setShipNum(String shipNum){
        this.shipNum = shipNum;
    }
    
	/**
	 * 获取发货单号
	 * @return shipNum
	 */    
    public String getShipNum(){
        return this.shipNum;
    }    
    
	/**
	 * 设置发货时间
	 * @param shipAt 发货时间
	 */    
    public void setShipAt(java.util.Date shipAt){
        this.shipAt = shipAt;
    }
    
	/**
	 * 获取发货时间
	 * @return shipAt
	 */    
    public java.util.Date getShipAt(){
        return this.shipAt;
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