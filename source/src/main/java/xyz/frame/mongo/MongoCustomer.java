package xyz.frame.mongo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 客户信息 mongoPO
 * @author shisp
 * @date 2018-4-12 09:00:20
 */
@Document(collection = "customer")
public class MongoCustomer implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
	 * ID
	 */
	@Id
    private String id;
	/**
	 * groupId
	 */
	private Long groupId;
	/**
	 * name
	 */
	private String name;
	/**
	 * phone
	 */
    private String phone;
    
    private Date createAt;
    
    private Date updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "MongoCustomer [id=" + id + ", groupId=" + groupId + ", name=" + name + ", phone=" + phone + ", createAt=" + createAt + ", updateAt=" + updateAt
                + "]";
    }
}