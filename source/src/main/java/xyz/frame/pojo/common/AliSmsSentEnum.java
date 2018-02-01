package xyz.frame.pojo.common;

/**
 * @author shi 阿里短信发送参数枚举
 */
public enum AliSmsSentEnum {

	smsSent("https://eco.taobao.com/router/rest", "23752645", "38ff4b9a9e0d9708632d58c751819c86");

	private AliSmsSentEnum(String url, String appKey, String appSecret) {
		this.url = url;
		this.appKey = appKey;
		this.appSecret = appSecret;
	}

	private String url;
	private String appKey;
	private String appSecret;
	
	public String getUrl() {
		return url;
	}
	public String getAppKey() {
		return appKey;
	}
	public String getAppSecret() {
		return appSecret;
	}

}
