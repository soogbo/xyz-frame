package xyz.frame.redis;

import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xyz.frame.json.FrameJsonUtils;

/**
 * redis工具类
 * 
 * @author marshal.liu
 */
@Component
public class FrameworkRedisUtils {

    /**
     * redis客户端连接
     */
    @Autowired
    private FrameworkRedisService _redisService;
    private static FrameworkRedisService redisService;

    /**
     * 初始化
     */
    @PostConstruct
    public void initializer() {
        redisService = _redisService;
    }

    /**
     * 设置值到redis
     * 
     * @param key
     * @param value
     * @param expire
     *            失效时间，单位秒
     */
    public static void put(Object key, Object value, int expire) {
        if (value == null) {
            return;
        }
        String keyStr = FrameJsonUtils.toJson(key);
        String valueStr = FrameJsonUtils.toJson(value);
        redisService.setex(keyStr, expire, valueStr);
    }

    /**
     * 设置值到redis
     * 
     * @param key
     * @param value
     */
    public static void put(Object key, Object value) {
        if (value == null) {
            return;
        }
        String keyStr = FrameJsonUtils.toJson(key);
        String valueStr = FrameJsonUtils.toJson(value);
        redisService.set(keyStr, valueStr);
    }

    /**
     * 设置值到redis 先判断是否存在，如果存在不设置，如果不存在就设置
     * 
     * @param key
     * @param value
     * @return
     */
    public static Object putIfAbsent(Object key, Object value) {
        if (value == null) {
            return value;
        }
        String keyStr = FrameJsonUtils.toJson(key);
        if (!redisService.exists(keyStr)) {
            String valueStr = FrameJsonUtils.toJson(value);
            redisService.set(keyStr, valueStr);
        }
        return value;
    }

    /**
     * 获取缓存值
     * 
     * @param key
     * @return
     */
    public static Object get(Object key) {
        String keyStr = FrameJsonUtils.toJson(key);
        String valueStr = redisService.get(keyStr);
        return FrameJsonUtils.fromJson(valueStr, Object.class);
    }

    /**
     * 删除Key
     * 
     * @param key
     */
    public static void del(Object key) {
        String keyStr = FrameJsonUtils.toJson(key);
        redisService.del(keyStr);
    }

    /**
     * 清空redis
     */
    public static void flushDB() {
        redisService.flushDB();
    }

    /**
     * size
     * 
     * @return
     */
    public static Long dbSize() {
        return redisService.dbSize();
    }

    /**
     * keys
     * 
     * @param regex
     * @return
     */
    public static Set<String> keys(String pattern) {
        return redisService.keys(pattern);
    }

}
