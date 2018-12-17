package xyz.frame.distrbutelock;

/**
 * @Author wanghao created by 2018-08-31
 * 分布式锁枚举
 */
public enum  DistrbuteLockEnum {

    /**
     * 锁定
     */
    LOCK(1,"已锁"),

    /**
     * 未锁定
     */
    UNLOCK(0,"未锁");


    private Integer lockStatus;
    private String text;


    DistrbuteLockEnum(Integer lockStatus, String text) {
        this.lockStatus = lockStatus;
        this.text = text;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public String getText() {
        return text;
    }

}
