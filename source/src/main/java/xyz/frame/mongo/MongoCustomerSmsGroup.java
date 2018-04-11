package xyz.frame.mongo;

import java.io.Serializable;
import java.util.List;

/**
 * 短信分组
 * @author lius
 */
public class MongoCustomerSmsGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 短信列表
	 */
	private List<MongoCustomerSmsDetail> smsList;
	/**
	 * 最近一次短信内容
	 */
	private MongoCustomerSmsDetail lastSms;
	 
	public List<MongoCustomerSmsDetail> getSmsList() {
		return smsList;
	}
	public void setSmsList(List<MongoCustomerSmsDetail> smsList) {
		this.smsList = smsList;
	}
	public MongoCustomerSmsDetail getLastSms() {
		return lastSms;
	}
	public void setLastSms(MongoCustomerSmsDetail lastSms) {
		this.lastSms = lastSms;
	}
	
}