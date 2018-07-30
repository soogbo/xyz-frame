package xyz.frame.configure.redis;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis工具：使用jedis方式
 * 
 * @author shisp
 * @date 2018-7-30 10:06:01
 */
@Component
public class FrameRedis {
    private static final Logger logger = LoggerFactory.getLogger(FrameRedis.class);

    /**
     * redis过期时间是30分钟30*60
     */
    private int expireTime = 1800;
    /**
     * 计数器的过期时间默认2天
     */
    private int countExpireTime = 2 * 24 * 3600;

    @Resource(name = "frameJedisPool")
    private JedisPool jedisPool;

    /**
     * 从连接池获取redis连接
     * 
     * @return
     */
    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            logger.info("getJedis error", e);
        }
        return jedis;
    }

    /**
     * 回收redis连接
     * 
     * @param jedis
     */
    public void recycleJedis(Jedis jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception e) {
                logger.info("recycle Jedis error", e);
            }
        }
    }

    /**
     * 保存字符串数据
     * 
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        Jedis jedis = getJedis();
        if (jedis != null) {
            try {
                jedis.set(key, value);
            } catch (Exception e) {
                logger.info("setString error,key={},value={}", key, value, e);
            } finally {
                recycleJedis(jedis);
            }
        }
    }

    /**
     * 获取字符串类型的数据
     * 
     * @param key
     * @return
     */
    public String getString(String key) {
        Jedis jedis = getJedis();
        String result = "";
        if (jedis != null) {
            try {
                result = jedis.get(key);
            } catch (Exception e) {
                logger.info("getString error,key={}", key, e);
            } finally {
                recycleJedis(jedis);
            }
        }
        return result;
    }

    /**
     * 删除字符串数据
     * 
     * @param key
     */
    public void delString(String key) {
        Jedis jedis = getJedis();
        if (jedis != null) {
            try {
                jedis.del(key);
            } catch (Exception e) {
                logger.info("delString error,key={}", key, e);
            } finally {
                recycleJedis(jedis);
            }
        }
    }

    /**
     * 保存byte类型数据
     * 
     * @param key
     * @param value
     */
    public void setObject(byte[] key, byte[] value, Integer expireTime) {
        if (null == expireTime) {
            expireTime = this.expireTime;
        }
        Jedis jedis = getJedis();
        if (jedis != null) {
            try {
                if (!jedis.exists(key)) {
                    jedis.set(key, value);
                }
                // redis中session过期时间
                jedis.expire(key, expireTime);
            } catch (Exception e) {
                logger.info("setObject error,key={}value={}", key, String.valueOf(value), e);
            } finally {
                recycleJedis(jedis);
            }
        }
    }

    /**
     * 获取byte类型数据
     * 
     * @param key
     * @return
     */
    public byte[] getObject(byte[] key) {
        Jedis jedis = getJedis();
        byte[] bytes = null;
        if (jedis != null) {
            try {
                bytes = jedis.get(key);
            } catch (Exception e) {
                logger.info("getObject error,key={}", String.valueOf(key), e);
            } finally {
                recycleJedis(jedis);
            }
        }
        return bytes;
    }

    /**
     * 更新byte类型的数据，主要更新过期时间
     * 
     * @param key
     */
    public void updateObject(byte[] key,Integer expireTime) {
        if (null==expireTime) {
            expireTime = this.expireTime;
        }
        Jedis jedis = getJedis();
        if (jedis != null) {
            try {
                // redis中session过期时间
                jedis.expire(key, expireTime);
            } catch (Exception e) {
                logger.info("updateObject error,key={}", key, String.valueOf(key), e);
            } finally {
                recycleJedis(jedis);
            }
        }
    }

    /**
     * key对应的整数value加1
     * 
     * @param key
     */
    public void inc(String key, Integer countExpireTime) {
        if (null == countExpireTime) {
            countExpireTime = this.countExpireTime;
        }
        Jedis jedis = getJedis();
        if (jedis != null) {
            try {
                if (!jedis.exists(key)) {
                    jedis.set(key, "1");
                    jedis.expire(key, countExpireTime);
                } else {
                    // 加1
                    jedis.incr(key);
                }
            } catch (Exception e) {
                logger.info("recycleJedis,key={}", key, e);
            } finally {
                recycleJedis(jedis);
            }
        }
    }

    /**
     * 获取所有keys
     * 
     * @param pattern
     * @return
     */
    public Set<String> getAllKeys(String pattern) {
        Jedis jedis = getJedis();
        if (jedis != null) {
            try {
                return jedis.keys(pattern);
            } catch (Exception e) {
                logger.info("getAllKeys error,pattern={}", pattern, e);
            } finally {
                recycleJedis(jedis);
            }
        }
        return new HashSet<>();
    }

}
