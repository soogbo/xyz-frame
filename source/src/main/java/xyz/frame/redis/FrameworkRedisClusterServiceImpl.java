package xyz.frame.redis;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * 集群模式时的实现方式
 * 
 * @author marshal.liu
 */
public class FrameworkRedisClusterServiceImpl implements FrameworkRedisService {
	private static Logger log = LoggerFactory.getLogger(FrameworkRedisClusterServiceImpl.class);
	private JedisCluster jedisCluster;
	private int expire;

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	/**
	 * 设置
	 * 
	 * @param keyStr
	 *            键
	 * @param valueStr
	 *            值
	 */
	@Override
	public void set(String keyStr, String valueStr) {
		setex(keyStr, expire, valueStr);
	}
	
	/**
	 * 设置
	 * 
	 * @param key 键
	 * @param value 值
	 */
	@Override
	public void set(byte[] key, byte[] value) {
		setex(key, expire, value);
	}	

	/**
	 * 设置
	 * @param keyStr 键
	 * @param expire 有效时间
	 * @param valueStr 值
	 */
	@Override
	public void setex(String keyStr, int expire, String valueStr) {
		if (expire > 0) {			
			jedisCluster.setex(keyStr, expire, valueStr);
		} else {
			jedisCluster.set(keyStr, valueStr);
		}
	}
	
	/**
	 * 设置
	 * @param key 键
	 * @param expire 有效时间
	 * @param value 值
	 */
	@Override
	public void setex(byte[] key, int expire, byte[] value) {
		if (expire > 0) {			
			jedisCluster.setex(key, expire, value);
		} else {
			jedisCluster.set(key, value);
		}
	}	

	/**
	 * 是否存在
	 * 
	 * @param keyStr
	 * @return
	 */
	@Override
	public boolean exists(String keyStr) {
		return jedisCluster.exists(keyStr);
	}
	
	/**
	 * 是否存在
	 * 
	 * @param keyStr
	 * @return
	 */
	@Override
	public boolean exists(byte[] key) {
		return jedisCluster.exists(key);
	}	

	/**
	 * 获取
	 * 
	 * @param keyStr
	 * @return
	 */
	@Override
	public String get(String keyStr) {
		return jedisCluster.get(keyStr);
	}
	
	/**
	 * 获取
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public byte[] get(byte[] key) {
		return jedisCluster.get(key);
	}	

	/**
	 * 删除
	 * 
	 * @param keyStr
	 */
	@Override
	public void del(String keyStr) {
		jedisCluster.del(keyStr);
	}
	
	/**
	 * 删除
	 * 
	 * @param key
	 */
	@Override
	public void del(byte[] key) {
		jedisCluster.del(key);
	}	

	/**
	 * 清空
	 */	
	@Override
	public void flushDB() {				
		flushDB("*");
	}
	
	
	/**
	 * 清空符合匹配规则的键
	 * @param pattern
	 */
	@Override
	public void flushDB(String pattern) {
		Set<String> keyset = keys(pattern);
		if(keyset!=null){
			for(String key:keyset){
				del(key);
			}
		}		
	}

	/**
	 * dbsize
	 */	
	@Override
	public Long dbSize() {
		long size = 0;
		Set<String> set = keys("*");
		if(set!=null){
			size = set.size();
		}
		return size;
	}

	/**
	 * 获取符合匹配规则的键
	 * 
	 * @param pattern
	 * @return
	 */
	@Override
	public Set<String> keys(String pattern) {
		Set<String> resultSet = new HashSet<String>();
		for (String redisKey : jedisCluster.getClusterNodes().keySet()) {
			JedisPool jp = null;
			Jedis jedis = null;
			try {
				jp = jedisCluster.getClusterNodes().get(redisKey);
				if (jp == null || jp.isClosed()) {
					continue;
				}
				jedis = jp.getResource();
				Set<String> tmpSet = jedis.keys(pattern);
				if (tmpSet != null) {
					resultSet.addAll(tmpSet);
				}
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			} finally {
				if (jedis != null) {					
					jedis.close();
				}
			}
		}
		return resultSet;
	}

	/**
	 * 往列表中添加值
	 * @param key
	 * @param strings
	 * @return
	 */
	@Override
	public Long rpush(String key,String ...strings){
		return rpush(key, expire, strings);
	}
	
	/**
	 * 往列表中添加值
	 * @param key
	 * @param seconds 有效时间
	 * @param strings
	 * @return
	 */
	@Override
	public Long rpush(String key,int seconds,String ...strings){
		Long result = jedisCluster.rpush(key, strings);
		if (seconds > 0) {
			jedisCluster.expire(key, seconds);	
		}		
		return result; 
	}
	
	/**
	 * 往列表前面添加值
	 * 
	 * @param key
	 * @param strings
	 * @return
	 */
	@Override
	public Long lpush(String key, String... strings) {
		return lpush(key, expire, strings);
	}
		
	/**
	 * 往列表前面添加值
	 * @param key
	 * @param expire 有效时间
	 * @param strings
	 * @return
	 */
	@Override
	public Long lpush(String key, int seconds, String... strings) {
		Long result = jedisCluster.lpush(key, strings);
		if (seconds > 0) {
			jedisCluster.expire(key, seconds);	
		}		
		return result; 
	}
	
	/**
	 * 取出并获取列表中的最后一个元素
	 * @param key
	 * @return
	 */
	@Override
	public String rpop(String key){
		return jedisCluster.rpop(key);
	}
	
	/**
	 * 获取并取出列表中的第一个元素
	 * @param key
	 * @return
	 */
	@Override
	public String lpop(String key){
		return jedisCluster.lpop(key);
	}

	/**
	 * 获取列表长度
	 * @param key
	 * @return
	 */
	@Override
	public Long llen(String key) {
		return jedisCluster.llen(key);
	}

	/**
	 * 获取列表范围值
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@Override
	public List<String> lrange(String key, long start, long end) {		 
		return jedisCluster.lrange(key, start, end);
	}

	/**
	 * 从列表中删除元素
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 */
	@Override
	public Long lrem(String key, long count, String value) {
		return jedisCluster.lrem(key, count, value);
	}

	/**
	 * 修剪列表到指定的范围内
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@Override
	public String ltrim(String key, long start, long end) {		 
		return jedisCluster.ltrim(key, start, end);
	}
	
	/**
	 * 设置哈希值
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	@Override
	public long hset(String key, String field, String value) {		
		return hset(key, this.expire, field, value);
	}
	
	/**
	 * 设置哈希值
	 * @param key
	 * @param seconds
	 * @param field
	 * @param value
	 * @return
	 */
	@Override
	public long hset(String key,int seconds, String field, String value) {
		long result = jedisCluster.hset(key, field, value);
		if (seconds > 0) {
			jedisCluster.expire(key, seconds);	
		}		
		return result;	 
	}
	
	/**
	 * 设置哈希值
	 * @param key
	 * @param fields
	 * @param values
	 * @return
	 */
	@Override
	public long hset(String key, String[] fields, String[] values) {		
		return hset(key, this.expire, fields, values);
	}
	
	/**
	 * 设置哈希值
	 * @param key
	 * @param seconds
	 * @param fields
	 * @param values
	 * @return
	 */
	@Override
	public long hset(String key, int seconds, String[] fields, String[] values) {
		long result = 0;
		for(int i=0;i<fields.length;i++){				
			result += jedisCluster.hset(key, fields[i], values[i]);
		}		
		if (seconds > 0) {
			jedisCluster.expire(key, seconds);	
		}
		return result;
	}
	
	/**
	 * 获取哈希指定字段的值
	 * @param key
	 * @param field
	 * @return
	 */
	@Override
	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}
	
	/**
	 * 获取哈希所有的值
	 * @param key
	 * @return
	 */
	@Override
	public Map<String, String> hgetAll(String key) {
		return jedisCluster.hgetAll(key);
	}	
	
	/**
	 * 删除哈希表指定的field
	 * @param key
	 * @param field
	 * @return
	 */
	@Override
	public long hdel(String key, String... fields) {		 				
		return jedisCluster.hdel(key, fields);		 
	}

	@Override
	public long expire(String key, int seconds) {		 
		return jedisCluster.expire(key, seconds);
	}
	
	@Override
	public long expire(byte[] key, int seconds) {		 
		return jedisCluster.expire(key, seconds);
	}

	@Override
	public long sadd(String key, String... members) {
		return sadd(key,this.expire,members);
	}

	@Override
	public long sadd(String key, int seconds, String... members) {
		long result = jedisCluster.sadd(key, members);
		if (seconds > 0) {
			jedisCluster.expire(key, seconds);	
		}
		return result;
	}

	@Override
	public long scard(String key) {
		return jedisCluster.scard(key);
	}

	@Override
	public boolean sismember(String key, String member) {
		return jedisCluster.sismember(key,member);
	}

	@Override
	public Set<String> smembers(String key) {
		return jedisCluster.smembers(key);
	}

	@Override
	public long srem(String key, String... members) {
		return jedisCluster.srem(key,members);
	}		

	
}
