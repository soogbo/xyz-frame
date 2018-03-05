package xyz.frame.pojo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 账单还款更新Vo
 */
public class UpdateBillVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 账单明细List
     */
    private List<UpdateBillItemVo> billItemList;
    
    /**
     * loan账单ID
     */
    private Long billId;

    /**
     * loan订单ID
     */
    private Long orderId;

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
    private Integer totalPrincipal;

    /**
     * 当期本金余额
     */
    private Integer principalBalance;

    /**
     * 累计管理费（手续费）
     */
    private Integer totalManageFee;

    /**
     * 管理费余额
     */
    private Integer manageFeeBalance;

    /**
     * 累计利息
     */
    private Integer totalInterest;

    /**
     * 利息余额
     */
    private Integer interestBalance;

    /**
     * 累计滞纳费
     */
    private Integer totalOverdueFee;

    /**
     * 滞纳费余额
     */
    private Integer overdueFeeBlance;
    
    /**
     * 累计逾期违约金
     */
    private Integer totalPenaltyFee;
    
    /**
     * 逾期违约金余额
     */
    private Integer penaltyFeeBlance;

    /**
     * 应还日 yyyy-MM-dd
     */
    private String nextPayDate;

    /**
     * 状态，1-未结清，2-已结清
     */
    private Integer status;

    /**
     * 逾期标识，1-逾期，2-正常
     */
    private Integer overdueMark;

    /**
     * 逾期天数
     */
    private Integer overdueDays;

    /**
     * 还款时间 yyyy-MM-dd HH:mm:ss
     */
    private String repaymentDate;
    

    public List<UpdateBillItemVo> getBillItemList() {
        return billItemList;
    }

    public void setBillItemList(List<UpdateBillItemVo> billItemList) {
        this.billItemList = billItemList;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Integer getTotalPrincipal() {
        return totalPrincipal;
    }

    public void setTotalPrincipal(Integer totalPrincipal) {
        this.totalPrincipal = totalPrincipal;
    }

    public Integer getPrincipalBalance() {
        return principalBalance;
    }

    public void setPrincipalBalance(Integer principalBalance) {
        this.principalBalance = principalBalance;
    }

    public Integer getTotalManageFee() {
        return totalManageFee;
    }

    public void setTotalManageFee(Integer totalManageFee) {
        this.totalManageFee = totalManageFee;
    }

    public Integer getManageFeeBalance() {
        return manageFeeBalance;
    }

    public void setManageFeeBalance(Integer manageFeeBalance) {
        this.manageFeeBalance = manageFeeBalance;
    }

    public Integer getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Integer totalInterest) {
        this.totalInterest = totalInterest;
    }

    public Integer getInterestBalance() {
        return interestBalance;
    }

    public void setInterestBalance(Integer interestBalance) {
        this.interestBalance = interestBalance;
    }

    public Integer getTotalOverdueFee() {
        return totalOverdueFee;
    }

    public void setTotalOverdueFee(Integer totalOverdueFee) {
        this.totalOverdueFee = totalOverdueFee;
    }

    public Integer getOverdueFeeBlance() {
        return overdueFeeBlance;
    }

    public void setOverdueFeeBlance(Integer overdueFeeBlance) {
        this.overdueFeeBlance = overdueFeeBlance;
    }

    public Integer getTotalPenaltyFee() {
        return totalPenaltyFee;
    }

    public void setTotalPenaltyFee(Integer totalPenaltyFee) {
        this.totalPenaltyFee = totalPenaltyFee;
    }

    public Integer getPenaltyFeeBlance() {
        return penaltyFeeBlance;
    }

    public void setPenaltyFeeBlance(Integer penaltyFeeBlance) {
        this.penaltyFeeBlance = penaltyFeeBlance;
    }

    public String getNextPayDate() {
        return nextPayDate;
    }

    public void setNextPayDate(String nextPayDate) {
        this.nextPayDate = nextPayDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOverdueMark() {
        return overdueMark;
    }

    public void setOverdueMark(Integer overdueMark) {
        this.overdueMark = overdueMark;
    }

    public Integer getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

}