package xyz.frame.service.impl;

import org.springframework.stereotype.Service;

import xyz.frame.service.DubboDemoService;

@Service("dubboDemoService")
public class DubboDemoServiceImpl implements DubboDemoService {

    @Override
    public String providerService(String classCode) {
        System.out.println("dubbo 提供服务，接收参数：" + classCode);
        return classCode + ":" +System.currentTimeMillis();
    }

    @Override
    public String consumerService(String classCode) {
        System.out.println("dubbo 消费服务，接收参数：" + classCode);
        return classCode + ":" +System.currentTimeMillis();
    }

}
