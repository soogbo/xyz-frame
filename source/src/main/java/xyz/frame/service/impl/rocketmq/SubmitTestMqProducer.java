package xyz.frame.service.impl.rocketmq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import xyz.frame.common.GroupTopicTagEnum;
import xyz.frame.configure.rocketmq.RocketMQMessageService;

/**
 * mqProducer
 * Created by shisp on 2017年11月7日.
 */
@Service
public class SubmitTestMqProducer {
	private static final Logger logger = LoggerFactory.getLogger(SubmitTestMqProducer.class);
	
    @Autowired
    private RocketMQMessageService rocketMQMessageService;
    
    public void sendOperationToMq(List<Integer> paramMqBo){
        //--------------------------
        List<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, 1,2,3,4,5,6);
        paramMqBo = arrayList;
        //--------------------------
        
        try{
            logger.info("测试发送mq开始:{}",paramMqBo);
            String jsonString = JSON.toJSONString(paramMqBo, true);
            rocketMQMessageService.send(GroupTopicTagEnum.testMq.getTopic(), jsonString);
            logger.info("测试发送mq结束");
        }catch(Exception ex){
            logger.error("测试发送mq失败:{}",ex.getMessage(),ex);
        }
	}
}
