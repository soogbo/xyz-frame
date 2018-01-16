package xyz.frame.pojo.common;

/**
 * @author shi 阿里短信签名/模板枚举
 */
public enum AliSmsFreeSignNameEnum {

	soogbo测试("soogbo测试"),
	
 	SMS_64190036("SMS_64190036");
	
	private AliSmsFreeSignNameEnum(String name) {
		this.name = name;
	}

	private String name;
	
	public String getName() {
		return name;
	}

}
