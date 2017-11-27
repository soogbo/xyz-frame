package xyz.frame.common;

/**
 * mq：group topic tag枚举
 * Created by shisp on 2017年11月24日.
 */
public enum GroupTopicTagEnum {
    //测试mq topic。。。
    testMq("test_mq","test_mq","test_mq");
	
	private GroupTopicTagEnum(String group,String topic,String tag){
		this.group = group;
		this.topic = topic;
		this.tag = tag;
	}
	private String group;
	private String topic;
	private String tag;
	public String getGroup() {
		return group;
	}
	public String getTopic() {
		return topic;
	}
	public String getTag() {
		return tag;
	}
	
}
