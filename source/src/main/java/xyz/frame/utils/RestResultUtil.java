package xyz.frame.utils;

import xyz.frame.pojo.common.ResultEnum;

/**
 * 返回信息包装工具类
 */
public class RestResultUtil {

	private static ResponseResult<?> result;

	public static ResponseResult<?> success(Object object) {
		result = new ResponseResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), object);
		return result;
	}

	public static ResponseResult<?> success() {
		return success(null);
	}

	public static ResponseResult<?> error(int code, String msg) {
		result = new ResponseResult<>(code, msg, null);
		return result;
	}
}
