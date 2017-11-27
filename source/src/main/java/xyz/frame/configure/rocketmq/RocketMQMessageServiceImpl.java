package xyz.frame.configure.rocketmq;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import xyz.frame.common.ConfigKey;
import xyz.frame.utils.ConfigureProperties;
import xyz.frame.utils.ServiceException;

/**
 * rocketmq消息服务实现类
 * @author marshal.liu
 */
@Service
public class RocketMQMessageServiceImpl implements RocketMQMessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(RocketMQMessageServiceImpl.class);
	private DefaultMQProducer producer;
	
	/**
	 * 初始化
	 */
	@PostConstruct
	public void init(){
		/**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例
         * 注意：ProducerGroupName需要由应用来保证唯一
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        producer = new DefaultMQProducer();
        producer.setProducerGroup("collectionProducer");                
        producer.setNamesrvAddr(ConfigureProperties.getProperty(ConfigKey.CONSUMER_MQ_IP));
        producer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        producer.setVipChannelEnabled(false);
        try {        	
			producer.start();
		} catch (MQClientException e) {
			logger.error("rocketmq生产者初始化失败:{}",e.getMessage(),e);
			throw new RuntimeException(e);			
		}
	}
	
	/**
	 * 发送消息
	 * @param topic 主题
	 * @param value 值
	 */
	@Override
	public void send(String topic, String value) {
		Message msg = new Message();
		try {
		    msg.setTopic(topic);		
		    msg.setBody(value.getBytes("UTF-8"));
			producer.send(msg);			
		} catch (Exception e) {
			logger.error("rocketmq发送消息失败:{}",e.getMessage(),e);
			throw new ServiceException(11,"rocketmq发送消息失败:"+e.getMessage());
		}  
	}

	/**
	 * 消息接收接口
	 * @param topic 主题
	 * @return 消息客户端
	 */
	@Override
	public RocketMQMessageConsumer getMessageConsumer(String topic) {		
		return RocketMQMessageConsumerImpl.getMessageConsumer(topic);
	}
	
	/**
	 * 销毁
	 */
	@PreDestroy
	public void destroy(){
		if(producer!=null){			
			producer.shutdown();
		}
	}

}
