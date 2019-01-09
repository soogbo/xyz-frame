package xyz.frame.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.distrbutelock.db.DistributeDbLockUtil;
import xyz.frame.distrbutelock.db.DistributeLock;
import xyz.frame.distrbutelock.db.LockConstant;
import xyz.frame.exception.ServiceException;
import xyz.frame.pojo.common.ResultEnum;
import xyz.frame.pojo.po.ParamParam;
import xyz.frame.service.ParamParamService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * 分布式锁测试demo
 * 
 * @author shisp
 * @date 2018-12-17 17:53:27
 */
@RequestMapping("/distributeLock")
@RestController
public class DistributeDbLockController {

    @Autowired
    private ParamParamService paramParamService;
    
    @GetMapping("/addParam")
    public ResponseResult<?> addParam() {
        ParamParam paramParam = new ParamParam();
        paramParam.setClassId(1L);
        paramParam.setParamCode(String.format(LockConstant.LOCK_PREFIX,"测试锁唯一标识"));
        paramParam.setParamName("分布式锁参数测试");
        paramParam.setParamValue(String.valueOf(System.currentTimeMillis()));
        paramParam.setCreateAt(new Date());
        paramParam.setUpdateAt(new Date());
        
        DistributeLock lock = DistributeDbLockUtil.getDistributeLock(paramParam.getParamCode(), 30);
        if (lock.getLock()) {
            try {
                paramParamService.save(paramParam);
            } catch (Exception e) {
                
            }finally { //锁用完记得释放
                DistributeDbLockUtil.releaseDistributeLock(paramParam.getParamCode());
            }
        }else {
            throw new ServiceException(ResultEnum.DISTRIBUTE_LOCK_GE_FAIL);
        }
        
        return RestResultUtil.success(paramParam);
    }

}
