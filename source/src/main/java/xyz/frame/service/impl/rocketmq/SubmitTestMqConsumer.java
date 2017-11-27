package xyz.frame.service.impl.rocketmq;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import xyz.frame.pojo.common.GroupTopicTagEnum;
import xyz.frame.configure.rocketmq.RocketMQMessageCallback;
import xyz.frame.configure.rocketmq.RocketMQMessageConsumer;
import xyz.frame.configure.rocketmq.RocketMQMessageService;

/**
 * mqConsumer
 * Created by shisp on 2017年11月7日.
 */
@Service
public class SubmitTestMqConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SubmitTestMqConsumer.class); 
    @Autowired
    private RocketMQMessageService rocketMQMessageService;
    
    private RocketMQMessageConsumer rocketMQMessageConsumer;
    
    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        rocketMQMessageConsumer = rocketMQMessageService.getMessageConsumer(GroupTopicTagEnum.testMq.getTopic());
        rocketMQMessageConsumer.startup(GroupTopicTagEnum.testMq.getGroup(), new RocketMQMessageCallback(){
            @Override
            public void call(String topic, String msg) {
                receiveSubmit(topic,msg);
            }
        });
    }
    
    /**
     * 销毁
     */
    @PreDestroy
    public void destroy(){
        if(rocketMQMessageConsumer!=null){          
            rocketMQMessageConsumer.shutdown();
        }
    }
    
    
    public void receiveSubmit(String topic, String msg) {
        try {
            // json String 转对象两种方式：1.String.class对象  2.泛型类对象
            // List<Integer> paramBo = JSON.parseObject(msg,new TypeReference<List<Integer>>(){});
            // String paramBo = JSON.parseObject(msg,String.class);
            
            List<Integer> paramBo = JSON.parseObject(msg,new TypeReference<List<Integer>>(){});
            if(paramBo==null){
                logger.error("测试mq接口调用失败,msg为空");
                return;
            }
            processOperate(paramBo);//接收后执行后续操作
            logger.info("mq操作接口调用成功,paramBo.size:{}",paramBo.size());
        } catch (Exception ex) {
            logger.error("mq操作接口调用失败:{}", ex.getMessage(), ex);
        }
    }

    private void processOperate(List<Integer> paramBo) {
        System.out.println("mq消费参数：size"+paramBo.size());
        System.out.println("mq消费参数："+paramBo);
    }
}
