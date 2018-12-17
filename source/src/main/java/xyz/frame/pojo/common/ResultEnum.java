package xyz.frame.pojo.common;

/**
 * 返回结果枚举
 * 0：成功
 * 正数：业务异常
 * 负数：系统异常
 */
public enum ResultEnum {
    /**
     * 统一异常返回结果
     */
    UNKONW_ERROR(-1, "未知错误！"),
    SUCCESS(0, "成功"),
	SERVICE_ERROR(1, "业务处理错误！"),
    NULL_OBJECT(2, "处理对象为空！"),
    PASSWORD_ERROR(5001, "密码错误！"),
    
    LOGIN_SUCCESS(0, "登录成功"),
    LOGIN_FAILD(101, "登录失败"),
    SYSUSER_NOT_EXIST(102, "用户不存在"),
    SYSUSER_INVALID(103, "用户被禁用"),
    LOGIN_USER_ERROR(104, "登录用户获取错误"),
    INVENTORY_NO_RECORD(105, "进销存没有记录"),
    
    DISTRIBUTE_LOCK_GE_FAIL(201, "分布式锁获取失败"),
    
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

    ;

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
