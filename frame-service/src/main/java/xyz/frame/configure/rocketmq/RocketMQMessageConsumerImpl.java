package xyz.frame.configure.rocketmq;

import java.util.ArrayList;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.frame.exception.ServiceException;
import xyz.frame.pojo.common.ConfigKey;
import xyz.frame.utils.ConfigurePropertiesIoStream;

/**
 * rocketmq消息客户端
 * @author marshal.liu
 */
public class RocketMQMessageConsumerImpl implements RocketMQMessageConsumer {
	private static final Logger logger = LoggerFactory.getLogger(RocketMQMessageConsumerImpl.class);
	// 主题
	private List<String> topicList = new ArrayList<String>();
	// 业务回调接口
	private RocketMQMessageCallback callback = null;
	// 启动标志
	private volatile boolean isStartup = false;
	//rocketmq消息端
	private DefaultMQPushConsumer consumer = null;
	
	
	/**
	 * 启动接收消息
	 * @param consumerGroup 组名
	 * @param callback 回调接口
	 */
	@Override
	public void startup(String consumerGroup,RocketMQMessageCallback callback) {
		if (this.isStartup) {			
			return;
		}
		this.callback = callback;
		this.isStartup = true;
		consumer = new DefaultMQPushConsumer(consumerGroup);
		/*获取配置文件数据两种方式：
        1.使用ConfigurePropertiesIo类 通过io流读取configure.properties的配置
        2.使用@Value("${}")读取application.properties中配置 需要自定义类注解@Configuration @PropertySource制定文件路径名
        3.自定义类ReadProperties读取配置文件数据注入到类属性中
                            如果此类不被水平管理，不能使用23方法
        */
        consumer.setNamesrvAddr(ConfigurePropertiesIoStream.getProperty(ConfigKey.CONSUMER_MQ_IP));
        consumer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        for(String topic:topicList){
        	try {
				consumer.subscribe(topic, "*");
			} catch (MQClientException e) {
				logger.error("rocketmq消费者初始化失败:{}",e.getMessage(),e);
				throw new ServiceException(11,"rocketmq消费者初始化失败:"+e.getMessage());
			}	
        }
        //注册监听事件
        consumer.registerMessageListener(new MessageListenerConcurrently(){
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				try{
					for(MessageExt msg:msgs){											
						RocketMQMessageConsumerImpl.this.callback.call(msg.getTopic(), new String(msg.getBody(), "UTF-8"));	
					}						
				}catch(Exception ex){
					logger.error("消息处理失败:{}",ex.getMessage(),ex);
					return ConsumeConcurrentlyStatus.RECONSUME_LATER;
				}			
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
        	
        });
        try {
			consumer.start();
		} catch (MQClientException e) {
			logger.error("rocketmq消费者初始化失败:{}",e.getMessage(),e);
			throw new ServiceException(12,"rocketmq消费者初始化失败:"+e.getMessage());
		}
	}

	@Override
	public void shutdown() {
		//官网例子中没有调用关闭方法(如调用,在执行测试案例时会报异常)		
//		if(consumer!=null){			
//			consumer.shutdown();	
//		}
	}
	
	public static RocketMQMessageConsumer getMessageConsumer(String topic) {
		RocketMQMessageConsumerImpl consumer = new RocketMQMessageConsumerImpl();
		consumer.topicList.add(topic);
		return consumer; 
	}	

}
