package xyz.frame.service;

/**
 * dubbo 测试
 * @author shisp
 * @date 2018-2-26 20:21:24
 */
public interface DubboDemoService {


    String providerService(String classCode);

    String toConsumerService(String classCode);

}
