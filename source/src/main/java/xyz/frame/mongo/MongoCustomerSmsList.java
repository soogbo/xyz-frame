package xyz.frame.mongo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 客户短信列表
 * @author lius
 */
@Document(collection = "customer_sms_list")
public class MongoCustomerSmsList implements Serializable{
	private static final long serialVersionUID = -8328793724704783938L;
	/**
	 * ID
	 */
	@Id
    private String id;
	/**
	 * 案件ID
	 */
	private long caseId;
	/**
	 * 用户ID
	 */
    private long userId;
    /**
     * 短信分组列表
     */
    private List<MongoCustomerSmsGroup> smsGroupList;
    /**
     * 创建时间
     */
    private Date createAt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public long getCaseId() {
		return caseId;
	}
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public List<MongoCustomerSmsGroup> getSmsGroupList() {
		return smsGroupList;
	}
	public void setSmsGroupList(List<MongoCustomerSmsGroup> smsGroupList) {
		this.smsGroupList = smsGroupList;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	} 
}