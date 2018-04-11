package xyz.frame.mongo;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信内容
 *
 * @author lius
 */
public class MongoCustomerSmsDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 联系人姓名
     */
    private String name;
    /**
     * 联系人手机号
     */
    private String phoneNum;
    /**
     * 短信内容
     */
    private String smsBody;
    /**
     * 日期
     */
    private Date date;
    /**
     * 发送类型 1-接收 2-发送
     */
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MongoCustomerSmsDetail{" +
                "name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", smsBody='" + smsBody + '\'' +
                ", date=" + date +
                ", type=" + type +
                '}';
    }
}