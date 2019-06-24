package xyz.frame.configure.rocketmq;

/**
 * 消息客户端
 * @author marshal.liu
 */
public interface RocketMQMessageConsumer {
	/**
	 * 启动接收消息
	 * @param consumerGroup 组名
	 * @param callback 回调接口
	 */
	public void startup(String consumerGroup,RocketMQMessageCallback callback);
	
	/**
	 * 停止接收消息
	 */
	public void shutdown();
}
