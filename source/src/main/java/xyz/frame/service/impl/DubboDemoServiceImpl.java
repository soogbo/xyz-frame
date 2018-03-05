package xyz.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import xyz.frame.service.BillApiService;
import xyz.frame.service.DubboDemoService;

@Service("dubboDemoService")
public class DubboDemoServiceImpl implements DubboDemoService {

    @Reference
    private BillApiService billApiService;
    
    @Override
    public String providerService(String classCode) {
        System.out.println("dubbo 提供服务，接收参数：" + classCode);
        return classCode + ":" +System.currentTimeMillis();
    }

    @Override
    public String toConsumerService(String classCode) {
        System.out.println("dubbo 消费服务，接收参数：" + classCode);
        billApiService.updateProductCoreBill(null);
        return classCode + ":" +System.currentTimeMillis();
    }

}
