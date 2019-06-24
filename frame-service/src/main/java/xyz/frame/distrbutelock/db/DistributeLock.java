package xyz.frame.distrbutelock.db;

import java.util.Date;

/**
 * @Author wanghao created by 2018-08-31
 */
public class DistributeLock implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 线程ID
     */
    private String lockey;

    /**
     * 有效时间
     */
    private Date validTime;

    /**
     * 锁是否获得成功
     */
    private Boolean isLock = false;

    public DistributeLock() { }
    public DistributeLock(String lockey, Date validTime, Boolean isLock) {
        this.lockey = lockey;
        this.validTime = validTime;
        this.isLock = isLock;
    }

    public String getLockey() {
        return lockey;
    }

    public void setLockey(String lockey) {
        this.lockey = lockey;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean lock) {
        isLock = lock;
    }
}
