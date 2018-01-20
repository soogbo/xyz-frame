package xyz.frame.pojo.common;

/**
 * @author shi 阿里短信签名/模板枚举
 */
public enum AliSmsFreeSignNameEnum {

    // 签名
	soogbo测试("soogbo测试"),
	石保华种子站("石保华种子站"),
	
	// 模板id
 	SMS_64190036("SMS_64190036"),
 	SMS_122055063("SMS_122055063"),
 	;
	
	private AliSmsFreeSignNameEnum(String name) {
		this.name = name;
	}

	private String name;
	
	public String getName() {
		return name;
	}

}
