package xyz.frame.pojo.common;

public enum SystemErrorCodeEnum {
	/**
	 * 未登录 -100
	 */
	ERROR_CODE_NOT_LOGON(-100, "未登录"),
	/**
	 * 无权限访问 -101
	 */
	ERROR_CODE_NO_ACCESS(-101, "无权限访问"),
	/**
	 * 访问版本不支持 -102
	 */
	ERROR_CODE_VERSION(-102, "访问版本不支持"),
	/**
	 * 会话失效 -103
	 */
	ERROR_CODE_SESSION_INVALID(-103, "会话失效"),
	/**
	 * 请求无对应服务 -104
	 */
	ERROR_CODE_NO_SERVICE(-104, "请求无对应服务"),
	/**
	 * 请求接口错误 -105
	 */
	ERROR_CODE_INTERFACE(-105, "请求接口错误"),
	/**
	 * 返回对象无法做json对象转换 -106
	 */
	ERROR_CODE_JSON(-106, "返回对象无法做json对象转换"),
	/**
	 * 请求参数个数错误 -107
	 */
	ERROR_CODE_REQUEST(-107, "请求参数个数错误"),
	/**
	 * 请求参数类型错误 -108
	 */
	ERROR_CODE_PARAM(-108, "请求参数类型错误"),
	/**
	 * 请求参数无法转换java对象 -109
	 */
	ERROR_CODE_PARAM_JSON(-109, "请求参数无法转换java对象"),
	/**
	 * 后台业务处理遇到未知错误 -110
	 */
	ERROR_CODE_UNKNOWN(-110, "后台业务处理遇到未知错误"),
	/**
	 * entity定义错误，没有主键Id -111
	 */
	ERROR_CODE_ID(-111, "entity定义错误，没有主键Id"),
	/**
	 * 数据库访问错误 -112
	 */
	ERROR_CODE_DB(-112, "数据库访问错误"),
	/**
	 * 参数验证错误 -113
	 */
	ERROR_CODE_PARAM_VALID(-113, "参数验证错误"),
	/**
	 * 服务访问过频 -114
	 */
	ERROR_CODE_CALL_SERVICE_OVER_FREQUENCY(-114, "服务访问过频"),
	/**
	 * http method请求方法错误 -115
	 */
	ERROR_CODE_METHOD_UNMATCH(-115, "http method请求方法错误");
	
	/**
	 * 错误码
	 */
	private int errorCode;
	/**
	 * 错误消息
	 */
	protected String errorMsg;

	private SystemErrorCodeEnum(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
