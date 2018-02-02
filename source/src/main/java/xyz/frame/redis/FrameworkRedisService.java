package xyz.frame.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis服务
 * @author marshal.liu
 */
public interface FrameworkRedisService {

	/**
	 * 设置
	 * @param keyStr 键
	 * @param valueStr 值
	 */
	public void set(String keyStr,String valueStr);

	/**
	 * 设置
	 * @param key 键
	 * @param value 值
	 */
	public void set(byte[] key,byte[] value);
	
	/**
	 * 设置
	 * @param keyStr 键
	 * @param seconds 有效时间
	 * @param valueStr 值
	 */
	public void setex(String keyStr, int seconds, String valueStr);
	
	/**
	 * 设置
	 * @param keyS 键
	 * @param seconds 有效时间
	 * @param value 值
	 */
	public void setex(byte[] keyStr, int seconds, byte[] valueStr);
	
	/**
	 * 是否存在
	 * @param keyStr
	 * @return
	 */
	public boolean exists(String keyStr);
	
	/**
	 * 是否存在
	 * @param keyStr
	 * @return
	 */
	public boolean exists(byte[] key);
	
	/**
	 * 获取
	 * @param keyStr
	 * @return
	 */
	public String get(String keyStr);
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key);
	
	/**
	 * 删除
	 * @param keyStr
	 */
	public void del(String keyStr);
	
	/**
	 * 删除
	 * @param key
	 */
	public void del(byte[] key);	
	/**
	 * 清空
	 */
	public void flushDB();
	/**
	 * 清空符合匹配规则的键
	 * @param pattern
	 */
	public void flushDB(String pattern);
	/**
	 * size
	 * @return
	 */
	public Long dbSize();
	/**
	 * 获取符合匹配规则的键
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern);
	
	/**
	 * 往列表中添加值
	 * @param key
	 * @param strings
	 * @return
	 */
	public Long rpush(String key,String ...strings);
	
	/**
	 * 往列表中添加值
	 * @param key
	 * @param seconds 有效时间
	 * @param strings
	 * @return
	 */
	public Long rpush(String key,int seconds,String ...strings);
	
	/**
	 * 往列表前面添加值
	 * @param key
	 * @param strings
	 * @return
	 */
	public Long lpush(String key,String ...strings);
	
	/**
	 * 往列表前面添加值
	 * @param key
	 * @param seconds 有效时间
	 * @param strings
	 * @return
	 */
	public Long lpush(String key,int seconds,String ...strings);
	
	/**
	 * 取出并获取列表中的最后一个元素
	 * @param key
	 * @return
	 */
	public String rpop(String key);
	
	/**
	 * 获取并取出列表中的第一个元素
	 * @param key
	 * @return
	 */
	public String lpop(String key);
	
	/**
	 * 获取列表长度
	 * @param key
	 * @return
	 */
	public Long llen(String key);
	
	/**
	 * 获取列表范围值
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key, long start, long end);
	
	/**
	 * 从列表中删除元素
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 */
	public Long lrem(String key, long count, String value);
	
	/**
	 * 修剪列表到指定的范围内
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public String ltrim(String key, long start, long end);
	
	/**
	 * 设置哈希值
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public long hset(String key, String field, String value);
	
	/**
	 * 设置哈希值
	 * @param key
	 * @param seconds
	 * @param field
	 * @param value
	 * @return
	 */
	public long hset(String key,int seconds, String field, String value);
	
	/**
	 * 设置哈希值
	 * @param key
	 * @param fields
	 * @param values
	 * @return
	 */
	public long hset(String key,String[] fields, String[] values);
	
	/**
	 * 设置哈希值
	 * @param key
	 * @param seconds
	 * @param fields
	 * @param values
	 * @return
	 */
	public long hset(String key,int seconds, String[] fields, String[] values);
	
	/**
	 * 获取哈希指定字段的值
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field);
	/**
	 * 获取哈希所有的值
	 * @param key
	 * @return
	 */
	public Map<String,String> hgetAll(String key);
	
	/**
	 * 删除哈希表指定的field
	 * @param key
	 * @param field
	 * @return
	 */
	public long hdel(String key,String ...fields);
	
	/**
	 * 设置失效时间
	 * @param key 键
	 * @param seconds 失效时间，单位秒
	 * @return
	 */
	public long expire(String key, int seconds);
	
	/**
	 * 设置失效时间
	 * @param key 键
	 * @param seconds 失效时间，单位秒
	 * @return
	 */
	public long expire(byte[] key, int seconds);
	
    /**
     * 向集合添加一个或多个成员 
     * @param key 键
     * @param members 值列表
     * @return
     */
	public long sadd(String key,String ...members);
	
    /**
     * 向集合添加一个或多个成员 
     * @param key 键
     * @param seconds 失效时间
     * @param members 成员列表
     * @return
     */
	public long sadd(String key,int seconds,String ...members);
	
	/**
	 * 获取集合的成员数
	 * @param key 键
	 * @return
	 */
	public long scard(String key);
	
	/**
	 * 判断 member 元素是否是集合 key 的成员
	 * @param key 键
	 * @param member 成员
	 * @return
	 */
	public boolean sismember(String key,String member);
	
	/**
	 * 返回集合中的所有成员
	 * @param key 键
	 * @return
	 */
	public Set<String> smembers(String key); 
	
	/**
	 * 移除集合中一个或多个成员
	 * @param key 键
	 * @param members 成员列表
	 * @return
	 */
	public long srem(String key,String ...members);
}
