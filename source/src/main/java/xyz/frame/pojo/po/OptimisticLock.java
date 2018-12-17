package xyz.frame.pojo.po;

import javax.persistence.*;
import java.util.Date;
/**
 * @Author wanghao created by 2018-09-05
 */
@Entity
@Table(name = OptimisticLock.TABLE_NAME)
public class OptimisticLock {
    public static final String TABLE_NAME = "optimistic_lock";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lock_key")
    private String lockKey;

    /**
     * 处理状态：0-未锁，1-已锁
     */
    @Column(name = "lock_status")
    private Integer lockStatus;

    /**
     * 线程ID
     */
    @Column(name = "thread_id")
    private String threadId;

    /**
     * 有效时间单位秒
     */
    @Column(name = "valid_time")
    private Integer validTime;

    /**
     * 有效截止时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return lock_key
     */
    public String getLockKey() {
        return lockKey;
    }

    /**
     * @param lockKey
     */
    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    /**
     * 获取处理状态：0-未锁，1-已锁
     *
     * @return lock_status - 处理状态：0-未锁，1-已锁
     */
    public Integer getLockStatus() {
        return lockStatus;
    }

    /**
     * 设置处理状态：0-未锁，1-已锁
     *
     * @param lockStatus 处理状态：0-未锁，1-已锁
     */
    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    /**
     * 获取有效时间单位秒
     *
     * @return valid_time - 有效时间单位秒
     */
    public Integer getValidTime() {
        return validTime;
    }

    /**
     * 设置有效时间单位秒
     *
     * @param validTime 有效时间单位秒
     */
    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    /**
     * 获取有效截止时间
     *
     * @return end_time - 有效截止时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置有效截止时间
     *
     * @param endTime 有效截止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_at - 创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置更新时间
     *
     * @param updateAt 更新时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
}