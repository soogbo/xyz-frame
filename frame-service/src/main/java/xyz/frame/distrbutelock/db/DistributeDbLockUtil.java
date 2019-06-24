package xyz.frame.distrbutelock.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author wanghao create by 2018-08-31
 * 数据库分布式锁工具类
 */
@Component
public class DistributeDbLockUtil {

    private static DistributeDbLockUtil distributeDbLockUtil;

    @PostConstruct
    public void init() {
        distributeDbLockUtil = this;
    }

    @Autowired
    private OptimisticLockService optimisticLockService;

    /**
     * 获得锁
     *
     * @param lockKey
     * @return
     */
    public static DistributeLock getDistributeLock(String lockKey, int second) {
        return distributeDbLockUtil.optimisticLockService.getDistributeDbLock(lockKey, second);
    }

    /**
     * 获得锁
     *
     * @param lockKey
     * @return
     */
    public static boolean releaseDistributeLock(String lockKey) {
        return distributeDbLockUtil.optimisticLockService.releaseDistributeDbLock(lockKey);
    }
}
