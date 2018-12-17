package xyz.frame.distrbutelock;


import org.apache.commons.lang.RandomStringUtils;

/**
 * @Author wanghao create by 2018-08-31
 * 分布式锁相关常亮配置
 */
public class LockConstant {

    private LockConstant(){
    }
    
    /**
     * 线程唯一标识
     */
    public static final String UNIQ_SIGLE_THREAD_PREFIX = String.valueOf(System.currentTimeMillis()).
            concat(RandomStringUtils.random(10,true,true));

    /**
     * 分布式锁,默认锁前缀
     * %s为唯一标识(如case_id)
     */
    public static final String LOCK_PREFIX = "frame:lock:mysql:%s";

    /**
     * 分布式锁 默认有效时间
     */
    public static final Integer LOCK_DEFAULT_TIME = 0;

    /**
     * 分布式锁 默认截止时间
     */
    public static final String LOCK_DEFAULT_END_TIME = "2000-01-01 00:00:00";

    /**
     * 分布式锁 超时时间
     */
    public static final Integer LOCK_OVER_TIME = 3600;
}
