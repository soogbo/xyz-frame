package xyz.frame.utils;

import xyz.frame.pojo.common.ResultEnum;

/**
 * 通用返回对象
 * 
 * @author shisp
 * @date 2018-7-18 18:21:23
 * @param <T>
 */
public class GeneralResponse<T> {

    public static GeneralResponse<String> success() {
        return new GeneralResponse<>();
    }

    public static <T> GeneralResponse<T> success(T data) {
        return new GeneralResponse<>(data);
    }

    public static GeneralResponse<String> success(String code, String message) {
        return new GeneralResponse<>(code, message);
    }

    /**
     * 返回状态码
     */
    private String code = "0";

    /**
     * 返回消息
     */
    private String message = "success";

    /**
     * 返回数据
     */
    private T data;

    private GeneralResponse() {
        this(String.valueOf(ResultEnum.SUCCESS.getCode()), ResultEnum.SUCCESS.getMsg());
    }

    private GeneralResponse(T data) {
        this(String.valueOf(ResultEnum.SUCCESS.getCode()), ResultEnum.SUCCESS.getMsg());
        this.data = data;
    }

    private GeneralResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GeneralResponse [code=" + code + ", message=" + message + ", data=" + data + "]";
    }

}
