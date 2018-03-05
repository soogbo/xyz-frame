package xyz.frame.pojo.vo;

import java.io.Serializable;

/**
 * mq账单明细还款更新Vo
 */
public class MqUpdateBillVo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private UpdateBillVo billVo;
    private Long productSystemId;
    
    public MqUpdateBillVo() {
        super();
    }
    public MqUpdateBillVo(UpdateBillVo billVo, Long productSystemId) {
        super();
        this.billVo = billVo;
        this.productSystemId = productSystemId;
    }

    public UpdateBillVo getBillVo() {
        return billVo;
    }
    public void setBillVo(UpdateBillVo billVo) {
        this.billVo = billVo;
    }
    public Long getProductSystemId() {
        return productSystemId;
    }
    public void setProductSystemId(Long productSystemId) {
        this.productSystemId = productSystemId;
    }
}