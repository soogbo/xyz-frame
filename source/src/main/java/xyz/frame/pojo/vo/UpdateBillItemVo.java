package xyz.frame.pojo.vo;

import java.io.Serializable;

/**
 * 账单明细还款更新Vo
 */
public class UpdateBillItemVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 帐单明细ID
     */
    private Long billItemId;
    
    /**
     * 帐单ID
     */
    private Long billId;

    /**
     * 状态，1-未结清，2-已结清
     */
    private Integer status;
    
    /**
     * 总期数
     */
    private Integer totalPeriod;
    
    /**
     * 当前期数
     */
    private Integer currentPeriod;
    
    /**
     * 当期本金
     */
    private Integer principal;
    
    /**
     * 当期利息
     */
    private Integer interest;
    
    /**
     * 当期管理费（手续费）
     */
    private Integer manageFee;
    
    /**
     * 应还日yyyy-MM-dd
     */
    private String nextPayDate;

    public Long getBillItemId() {
        return billItemId;
    }

    public void setBillItemId(Long billItemId) {
        this.billItemId = billItemId;
    }
    
    public Long getBillId() {
        return billId;
    }
    
    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotalPeriod() {
        return totalPeriod;
    }

    public void setTotalPeriod(Integer totalPeriod) {
        this.totalPeriod = totalPeriod;
    }

    public Integer getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(Integer currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

    public Integer getManageFee() {
        return manageFee;
    }

    public void setManageFee(Integer manageFee) {
        this.manageFee = manageFee;
    }

    public String getNextPayDate() {
        return nextPayDate;
    }

    public void setNextPayDate(String nextPayDate) {
        this.nextPayDate = nextPayDate;
    }

}