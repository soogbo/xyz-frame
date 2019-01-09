package xyz.frame.distrbutelock.redis;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 *  Advice:注解方式redis分布式锁，锁方法
 *      应用：分布式注解方式的job
 *
 */
@Aspect
@Component
public class CacheLockAspect {

    private static final Logger logger = LoggerFactory.getLogger(CacheLockAspect.class);

    @Resource(name = "frameJedisPool")
    private JedisPool jedisPool;

    private DistributeLock distributeLock;

    @Around("@annotation(cacheLock)")
    public void process(ProceedingJoinPoint point, CacheLock cacheLock) {
        try {
            logger.info("LOGGER00100: cacheLock to lock: {} > {}", cacheLock.name(), point.getSignature());
            distributeLock = new DistributeLock(jedisPool, cacheLock.name());
            if (!distributeLock.lock()) {
                return;
            }
            point.proceed();
        } catch (Throwable throwable) {
            logger.error("LOGGER00300: cacheLock to lock point to process error: {} > {} : throwable {}", cacheLock.name(), point.getSignature(), throwable.getStackTrace());
        } finally {
            logger.info("LOGGER00500: cacheLock to unlock: {} > {}", cacheLock.name(), point.getSignature());
            distributeLock.unlock();
        }
    }

}