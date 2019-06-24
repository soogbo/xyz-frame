package xyz.frame.pojo.common;

/**
 * 状态标志枚举
 * 
 * @author shisp
 * @date 2018-7-27 15:03:48
 */
public enum ValidEnum {
    /**
     * 有效
     */
    VALID(1),
    /**
     * 失效
     */
    UN_VALID(0);

    private Integer isValid;

    private ValidEnum(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsValid() {
        return isValid;
    }

}
