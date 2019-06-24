package xyz.frame.pojo.common;

/**
 * 编码枚举
 */
public enum CharsetEnum {

    /**
     * 编码
     */
    UTF_8("UTF-8"), 
    GBK("GBK"), 
    ISO_8859_1("ISO-8859-1"),
    ;

    private String value;

    private CharsetEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
