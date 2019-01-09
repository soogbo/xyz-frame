package xyz.frame.distrbutelock.db;

import xyz.frame.exception.ServiceException;
import xyz.frame.json.FrameJsonUtils;
import xyz.frame.mapper.OptimisticLockMapper;
import xyz.frame.pojo.po.OptimisticLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author wanghao created by 2018-08-31
 * 分布式锁---数据库乐观锁实现
 */
@Service("optimisticLockService")
public class OptimisticLockServiceImpl implements OptimisticLockService {

    private static final Logger logger = LoggerFactory.getLogger(OptimisticLockServiceImpl.class);

    @Autowired
    private OptimisticLockMapper optimisticLockMapper;

    /**
     * 获得分布式锁
     * 流程:锁重入--->锁超时--->锁插入--->锁更新
     *
     * @param lockKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DistributeLock getDistributeDbLock(String lockKey, int second) {
        //线程ID
        String threadId = LockConstant.UNIQ_SIGLE_THREAD_PREFIX.concat(String.valueOf(Thread.currentThread().getId()));
        logger.info("开始尝试获得锁,key:{},thread-id:{}", lockKey, threadId);
        OptimisticLock lock = new OptimisticLock();
        lock.setLockKey(lockKey);
        lock.setThreadId(threadId);
        lock.setValidTime(second);
        //查询是否存在有效锁
        List<OptimisticLock> lockList = optimisticLockMapper.getValidLock(lockKey, DistrbuteLockEnum.LOCK.getLockStatus());
        if (CollectionUtils.isEmpty(lockList)) {
            //校验分布式锁信息
            if (checkDistributeLock(lock)) {
                return new DistributeLock(lockKey, lock.getEndTime(), true);
            } else {
                throw new ServiceException(1, "分布式锁获得失败");
            }

        }

        OptimisticLock lockCurrent = lockList.get(0);
        //锁状态可重入(thread_id)
        if (lockCurrent.getThreadId().equals(lock.getThreadId())) {
            logger.info("当前业务锁已重入,获锁成功,key:{},thread-id:{}", lockCurrent.getLockKey(), lockCurrent.getThreadId());
            return new DistributeLock(lockKey, lock.getEndTime(), true);
        }

        //锁已经存在
        logger.info("当前业务锁已存在,获锁失败,key:{},thread-id:{}", lockCurrent.getLockKey(), lockCurrent.getThreadId());
        return new DistributeLock(lockKey, null, false);

    }

    /**
     * 校验锁信息
     *
     * @param lock
     * @return
     */
    private boolean checkDistributeLock(OptimisticLock lock) {

        //查询是否存在该锁
        List<OptimisticLock> lockList = optimisticLockMapper.getValidLock(lock.getLockKey(), null);
        //没有该分布式锁信息,则插入相关信息
        if (CollectionUtils.isEmpty(lockList)) {
            return insertDistributeLock(lock);
        } else {
            OptimisticLock lockCurrent = lockList.get(0);
            if (DistrbuteLockEnum.UNLOCK.getLockStatus().equals(lockCurrent.getLockStatus())) {
                return updateDistributeLock(lock);
            } else {
                return insertDistributeLock(lock);
            }
        }
    }

    /**
     * 上锁
     *
     * @param lock
     * @return
     */
    private boolean insertDistributeLock(OptimisticLock lock) {
        OptimisticLock lockNew = new OptimisticLock();
        //超时锁
        BeanUtils.copyProperties(lock, lockNew);
        lockNew.setLockStatus(DistrbuteLockEnum.LOCK.getLockStatus());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, lockNew.getValidTime());
        lockNew.setEndTime(calendar.getTime());
        lockNew.setCreateAt(new Date());
        lockNew.setUpdateAt(new Date());
        String lockjson = FrameJsonUtils.toJson(lock);
        logger.info("插入的分布式锁信息:{}", lockjson);
        if (optimisticLockMapper.insert(lockNew) <= 0) {
            //获锁失败
            logger.info("获锁失败,key:{},thread-id:{}", lockNew.getLockKey(), lockNew.getThreadId());
            return false;
        }
        lock.setEndTime(lockNew.getEndTime());
        //获锁成功
        logger.info("获锁成功,key:{},thread-id:{}", lockNew.getLockKey(), lockNew.getThreadId());
        return true;
    }

    /**
     * 更新锁
     *
     * @param lock
     * @return
     */
    private boolean updateDistributeLock(OptimisticLock lock) {
        OptimisticLock lockNew = new OptimisticLock();
        BeanUtils.copyProperties(lock, lockNew);
        lockNew.setLockStatus(DistrbuteLockEnum.LOCK.getLockStatus());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, lockNew.getValidTime());
        lockNew.setEndTime(calendar.getTime());
        String lockjson = FrameJsonUtils.toJson(lock);
        logger.info("插入的分布式锁信息:{}", lockjson);

        OptimisticLock lockParam = new OptimisticLock();
        lockParam.setLockKey(lock.getLockKey());
        lockParam.setLockStatus(DistrbuteLockEnum.UNLOCK.getLockStatus());
        List<OptimisticLock> validLock = optimisticLockMapper.getValidLock(lockNew.getLockKey(), lockNew.getLockStatus());
        if (null == validLock) {
            logger.info("获锁失败,key:{},thread-id:{}", lockNew.getLockKey(), lockNew.getThreadId());
            return false;
        }
        lockNew.setId(lockParam.getId());
        lockNew.setUpdateAt(new Date());
        if (optimisticLockMapper.updateByPrimaryKeySelective(lockNew) <= 0) {
            logger.info("更新锁失败,key:{},thread-id:{}", lockNew.getLockKey(), lockNew.getThreadId());
            return false;
        }
            

        lock.setEndTime(lockNew.getEndTime());
        //获锁成功
        logger.info("获锁成功,key:{},thread-id:{}", lockNew.getLockKey(), lockNew.getThreadId());
        return true;
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean releaseDistributeDbLock(String lockKey) {
        //查询是否存在有效锁
        List<OptimisticLock> lockList = optimisticLockMapper.getValidLock(lockKey, null);
        if (CollectionUtils.isEmpty(lockList)) {
            logger.error("锁信息不存在,key:{}", lockKey);
            return false;
        }

        OptimisticLock lock = lockList.get(0);
        if (DistrbuteLockEnum.UNLOCK.getLockStatus().equals(lock.getLockStatus())) {
            logger.error("释放锁成功,key:{}", lockKey);
            return true;
        }

        lock.setLockStatus(DistrbuteLockEnum.UNLOCK.getLockStatus());
        lock.setValidTime(LockConstant.LOCK_DEFAULT_TIME);
        lock.setThreadId("");
        lock.setUpdateAt(new Date());
        try {
            lock.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                    parse(LockConstant.LOCK_DEFAULT_END_TIME));
        } catch (ParseException e) {
            logger.error("", e);
        }

        if (optimisticLockMapper.updateByPrimaryKeySelective(lock) <= 0) {
            logger.info("释放锁失败,key:{}", lockKey);
            return false;
        }
        logger.info("释放锁成功,key:{},key:{}", lockKey);
        return true;
    }
}