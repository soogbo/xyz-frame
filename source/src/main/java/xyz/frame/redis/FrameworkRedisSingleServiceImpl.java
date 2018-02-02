package xyz.frame.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 非集群模式时的实现方式
 * @author marshal.liu
 */
public class FrameworkRedisSingleServiceImpl implements FrameworkRedisService {

	private ShardedJedisPool shardedJedisPool;
	private  int expire;
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool){
		this.shardedJedisPool = shardedJedisPool;
	}
	public void setExpire(int expire){
		this.expire = expire;
	}
	
	/**
	 * 设置
	 * @param keyStr 键
	 * @param valueStr 值
	 */
	@Override
	public void set(String keyStr, String valueStr) {
		setex(keyStr, expire, valueStr);
	}
	
	/**
	 * 设置
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
	 * @param seconds 有效时间
	 * @param valueStr 值
	 */
	@Override
	public void setex(String keyStr, int seconds, String valueStr) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			 if(seconds>0){
				 jedis.setex(keyStr, seconds, valueStr);
			 }else{
				 jedis.set(keyStr,valueStr);				 
			 }
		}finally{
			jedis.close();			
		}		
	}
	
	/**
	 * 设置
	 * @param key 键
	 * @param seconds 有效时间
	 * @param value 值
	 */
	@Override
	public void setex(byte[] key, int seconds, byte[] value) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			 if(seconds>0){
				 jedis.setex(key, seconds, value);
			 }else{
				 jedis.set(key,value);				 
			 }
		}finally{
			jedis.close();			
		}		
	}	

	/**
	 * 是否存在
	 * @param keyStr
	 * @return
	 */
	@Override
	public boolean exists(String keyStr) {
		boolean flag = false;
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			flag = jedis.exists(keyStr);
		}finally{
			jedis.close();
		}		
		return flag;
	}
	
	/**
	 * 是否存在
	 * @param keyStr
	 * @return
	 */
	@Override
	public boolean exists(byte[] key) {
		boolean flag = false;
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			flag = jedis.exists(key);
		}finally{
			jedis.close();
		}		
		return flag;
	}	

	/**
	 * 获取
	 * @param keyStr
	 * @return
	 */
	@Override
	public String get(String keyStr) {		
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			return jedis.get(keyStr);
		}finally{
			jedis.close();
		}		
	}
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	@Override
	public byte[] get(byte[] key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			return jedis.get(key);
		}finally{
			jedis.close();
		}		
	}	

	/**
	 * 删除
	 * @param keyStr
	 */
	@Override
	public void del(String keyStr) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			jedis.del(keyStr);			
		}finally{
			jedis.close();
		}				
	}
	
	/**
	 * 删除
	 * @param keyStr
	 */
	@Override
	public void del(byte[] key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			jedis.del(key);			
		}finally{
			jedis.close();
		}				
	}	

	/**
	 * 清空
	 */
	@Override
	public void flushDB() {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			jedis.getShard("redis").flushDB();
		}finally{
			jedis.close();
		}
	}
	/**
	 * 清空符合匹配规则的键
	 * @param pattern
	 */
	@Override
	public void flushDB(String pattern) {		
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			Set<String> keyset = jedis.getShard("redis").keys(pattern);
			if(keyset!=null){
				for(String key:keyset){
					jedis.del(key);
				}
			}
		}finally{
			jedis.close();
		}
	}
	/**
	 * size
	 * @return
	 */
	@Override
	public Long dbSize() {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			return jedis.getShard("redis").dbSize();
		}finally{
			jedis.close();
		}
	}

	/**
	 * 获取符合匹配规则的键
	 * @param pattern
	 * @return
	 */
	@Override
	public Set<String> keys(String pattern) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{
			return jedis.getShard("redis").keys(pattern);
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 往列表前面添加值
	 * @param key
	 * @param strings
	 * @return
	 */
	@Override
	public Long rpush(String key, String... strings) {
		return rpush(key, expire, strings);
	}
	
	/**
	 * 往列表前面添加值
	 * @param key
	 * @param seconds 有效时间
	 * @param strings
	 * @return
	 */
	@Override
	public Long rpush(String key,int seconds,String ...strings){
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{		
			Long result = jedis.rpush(key, strings);
			if(seconds>0){
				jedis.expire(key, seconds);	
			}			
			return result;
		}finally{
			jedis.close();
		}	
	}
	
	/**
	 * 往列表前面添加值
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
	 * @param seconds 有效时间
	 * @param strings
	 * @return
	 */
	@Override
	public Long lpush(String key,int seconds,String ...strings){
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{		
			Long result = jedis.lpush(key, strings);
			if(seconds>0){
				jedis.expire(key, seconds);	
			}			
			return result;
		}finally{
			jedis.close();
		}	
	}
	
	/**
	 * 取出并获取列表中的最后一个元素
	 * @param key
	 * @return
	 */
	@Override
	public String rpop(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{		
			return jedis.rpop(key);
		}finally{
			jedis.close();
		}	
	}
	
	/**
	 * 获取并取出列表中的第一个元素
	 * @param key
	 * @return
	 */
	@Override
	public String lpop(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{		
			return jedis.lpop(key);
		}finally{
			jedis.close();
		}	
	}
	
	/**
	 * 获取列表长度
	 * @param key
	 * @return
	 */
	@Override
	public Long llen(String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{			
			return jedis.llen(key);
		}finally{
			jedis.close();
		}	
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
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{			
			return jedis.lrange(key, start, end);
		}finally{
			jedis.close();
		}	
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
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{			
			return jedis.lrem(key, count, value);
		}finally{
			jedis.close();
		}	
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
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{	
			return jedis.ltrim(key, start, end);
		}finally{
			jedis.close();
		}
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
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{						
			long result = jedis.hset(key, field, value);
			if(seconds>0){
				jedis.expire(key, seconds);	
			}			
			return result;
		}finally{
			jedis.close();
		}
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
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{	
			long result = 0;
			for(int i=0;i<fields.length;i++){				
				result += jedis.hset(key, fields[i], values[i]);
			}			
			if(seconds>0){
				jedis.expire(key, seconds);	
			}
			return result;
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 获取哈希指定字段的值
	 * @param key
	 * @param field
	 * @return
	 */
	@Override
	public String hget(String key, String field) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{			
			return jedis.hget(key, field);
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 获取哈希所有的值
	 * @param key
	 * @return
	 */
	@Override
	public Map<String, String> hgetAll(String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{						
			return jedis.hgetAll(key);
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 删除哈希表指定的field
	 * @param key
	 * @param field
	 * @return
	 */
	@Override
	public long hdel(String key, String... fields) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{						
			return jedis.hdel(key, fields);
		}finally{
			jedis.close();
		}		
	}
	
	@Override
	public long expire(String key, int seconds) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{						
			return jedis.expire(key, seconds);
		}finally{
			jedis.close();
		}	
	}	
	
	@Override
	public long expire(byte[] key, int seconds) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{						
			return jedis.expire(key, seconds);
		}finally{
			jedis.close();
		}	
	}
	@Override
	public long sadd(String key, String... members) {		 
		return sadd(key,this.expire,members);
	}
	
	@Override
	public long sadd(String key, int seconds, String... members) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{		
			Long result = jedis.sadd(key, members);			
			if(seconds>0){
				jedis.expire(key, seconds);	
			}			
			return result;
		}finally{
			jedis.close();
		}	
	}
	@Override
	public long scard(String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{		
			Long result = jedis.scard(key);						 
			return result;
		}finally{
			jedis.close();
		}
	}
	@Override
	public boolean sismember(String key, String member) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{		
			boolean result = jedis.sismember(key,member);						 
			return result;
		}finally{
			jedis.close();
		}
	}
	@Override
	public Set<String> smembers(String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{		
			Set<String> result = jedis.smembers(key);						 
			return result;
		}finally{
			jedis.close();
		}
	}
	@Override
	public long srem(String key, String... members) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try{		
			long result = jedis.srem(key,members);						 
			return result;
		}finally{
			jedis.close();
		}
	}			
}
