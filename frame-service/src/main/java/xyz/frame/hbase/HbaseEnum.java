package xyz.frame.hbase;

/**
 * hbase 表结构定义值
 * 
 * @author shisp
 * @date 2018-8-10 14:18:43
 */
public enum HbaseEnum {

    /**
     * rowkey
     */
    ROWKEY("row"),
    /**
     * family
     */
    FAMILY("family"),
    
    ;

    /**
     * rowkey
     */
    private String name;

    HbaseEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
