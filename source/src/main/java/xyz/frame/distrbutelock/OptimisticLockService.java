package xyz.frame.distrbutelock;


/**
 * @Athor wanghao created by 2018-08-31
 * 分布式锁---数据库乐观锁实现
 */

public interface OptimisticLockService {

    /**
     * 获取锁
     *
     * @param lockKey
     * @param second
     * @return
     */
    DistributeLock getDistributeDbLock(String lockKey, int second);

    /**
     * 释放锁
     *
     * @param lockKey
     * @return
     */
    boolean releaseDistributeDbLock(String lockKey);
}