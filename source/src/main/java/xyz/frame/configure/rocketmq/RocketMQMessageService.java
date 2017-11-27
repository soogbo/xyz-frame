package xyz.frame.configure.rocketmq;

/**
 * 消息服务接口
 * @author marshal.liu
 */
public interface RocketMQMessageService {
	/**
	 * 发送消息
	 * @param topic 主题
	 * @param value 值
	 */
	public void send(String topic,String value);
	
	/**
	 * 消息接收接口
	 * @param topic 主题
	 * @return
	 */
	public RocketMQMessageConsumer getMessageConsumer(String topic);
}
