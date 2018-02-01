package xyz.frame.pojo.vo;

import java.io.Serializable;

/**
 * 阿里短信发送参数vo
 * 
 * @author shisp
 */
public class AliSmsSentParamVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 公共回传参数
	 */
	private String extend;
	
	/**
	 * 短信类型，必选
	 */
	private String smsType;
	
	/**
	 * 短信签名，必选
	 */
	private String smsFreeSignName;
	
	/**
	 * 短信模板变量，{"code":"1234","product":"alidayu"} 对应模板${code}
	 */
	private String smsParamString;
	
	/**
	 * 短信接收号码，必选(11位手机号，多个','分隔，最多一次200个)
	 */
	private String recNum;
	
	/**
	 * 短信模板ID，必选
	 */
	private String smsTemplateCode;
	
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public String getSmsFreeSignName() {
		return smsFreeSignName;
	}
	public void setSmsFreeSignName(String smsFreeSignName) {
		this.smsFreeSignName = smsFreeSignName;
	}
	public String getSmsParamString() {
		return smsParamString;
	}
	public void setSmsParamString(String smsParamString) {
		this.smsParamString = smsParamString;
	}
	public String getRecNum() {
		return recNum;
	}
	public void setRecNum(String recNum) {
		this.recNum = recNum;
	}
	public String getSmsTemplateCode() {
		return smsTemplateCode;
	}
	public void setSmsTemplateCode(String smsTemplateCode) {
		this.smsTemplateCode = smsTemplateCode;
	}
}
