package xyz.frame.pojo.common;
/**
 * 请求方法枚举
 * @author marshal.liu
 */
public enum RequestMethodEnum {
	/**
	 * get方法
	 */
	GET,
	/**
	 * post方法
	 */
	POST,
	/**
	 * delete方法
	 */
	DELETE,
	/**
	 * 上传方法
	 */
	PUT;
	
	public static RequestMethodEnum getByName(String name){
		RequestMethodEnum requestMethodEnum = null;
		for(RequestMethodEnum tmp:RequestMethodEnum.values()){
			if(tmp.name().equals(name)){
				requestMethodEnum = tmp;
				break;
			}
		}
		return requestMethodEnum;
	}
}
