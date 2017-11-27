package xyz.frame.configure.rocketmq;

/**
 * 消息回调接口
 * @author marshal.liu
 */
public interface RocketMQMessageCallback {
	/**
	 * 回调方法
	 * @param topic 主题
	 * @param msg 接收到的消息
	 */
	public void call(String topic,String msg);
}
