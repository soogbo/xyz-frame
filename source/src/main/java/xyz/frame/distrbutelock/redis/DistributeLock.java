package xyz.frame.distrbutelock.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @author qiuwei
 * @create 2018-03-14 10:16
 **/
public class DistributeLock {
    private static final String LOCK_SUCCESS = "OK";
    private static final String UNLOCK_SUCCESS = "1";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    private static final int EXPIRE_TIME_VALUE = 500;

    /**
     * jedis pool
     */
    private JedisPool jedisPool;

    /**
     * 锁名称
     */
    private String name;

    /**
     * 通过token，保证仅加锁用户可以解锁
     */
    private String token;

    public DistributeLock(JedisPool jedisPool, String name) {
        this.jedisPool = jedisPool;
        this.name = name;
    }

    public boolean lock() {
        try (Jedis jedis = jedisPool.getResource()) {
            this.token = UUID.randomUUID().toString();
            String result = jedis.set(name, token, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, EXPIRE_TIME_VALUE);
            return LOCK_SUCCESS.equals(result);
        }
    }

    /**
     * 使用lua脚本保证操作原子性，上锁时value唯一，删除时先判断value是否存在再删除
     * @return
     */
    public boolean unlock() {
        String command = "if redis.call('get',KEYS[1]) == ARGV[1]\n" +
                "then\n" +
                "    return redis.call('del',KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";
        try (Jedis jedis = jedisPool.getResource()) {
            Object result = jedis.eval(command, 1, name, token);
            return UNLOCK_SUCCESS.equals(result.toString());
        }
    }

}
