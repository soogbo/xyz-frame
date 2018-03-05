package xyz.frame.service;

import xyz.frame.pojo.vo.UpdateBillVo;

/**
 * dubbo ceshi 
 * @author shisp
 * @date 2018-2-28 10:24:56
 */
public interface BillApiService {
    
    /**
     * 更新产品核心账单信息(API)<br>
     * http方法：post<br>
     *
     * @param billVo 账单更新Vo
     */    
    void updateProductCoreBill(UpdateBillVo billVo);

}
