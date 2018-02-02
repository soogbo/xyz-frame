package xyz.frame.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 内存版本时的实现方式（只开发时用）
 * @author marshal.liu
 */
public class FrameworkRedisMemoryServiceImpl implements FrameworkRedisService {

	/**
	 * 存储容器 
	 */
	private static final ConcurrentMap<Object, Object> store = new ConcurrentHashMap<Object, Object>();

	/**
	 * 失效时间,内存版本没有实现此功能
	 */	
	private  int expire;
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
		store.put(keyStr, valueStr);				
	}
	
	/**
	 * 设置
	 * @param key 键
	 * @param value 值
	 */
	@Override
	public void set(byte[] key, byte[] value) {
		store.put(key, value);				
	}	

	/**
	 * 设置
	 * @param keyStr 键
	 * @param expire 有效时间
	 * @param valueStr 值
	 */
	@Override
	public void setex(String keyStr, int expire, String valueStr) {
		store.put(keyStr, valueStr);		
	}
	
	/**
	 * 设置
	 * @param key 键
	 * @param expire 有效时间
	 * @param value 值
	 */
	@Override
	public void setex(byte[] key, int expire, byte[] value) {
		store.put(key, value);		
	}

	/**
	 * 是否存在
	 * @param keyStr
	 * @return
	 */
	@Override
	public boolean exists(String keyStr) {
		boolean flag = false;		
		flag = store.containsKey(keyStr);
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
		flag = store.containsKey(key);
		return flag;
	}	

	/**
	 * 获取
	 * @param keyStr
	 * @return
	 */
	@Override
	public String get(String keyStr) {		
		return (String)store.get(keyStr);
	}
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	@Override
	public byte[] get(byte[] key) {		
		return (byte[])store.get(key);
	}	

	/**
	 * 删除
	 * @param keyStr
	 */
	@Override
	public void del(String keyStr) {
		store.remove(keyStr);
	}
	
	/**
	 * 删除
	 * @param key
	 */
	@Override
	public void del(byte[] key) {
		store.remove(key);
	}	

	/**
	 * 清空
	 */
	@Override
	public void flushDB() {
		store.clear();		
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
	 * size
	 * @return
	 */
	@Override
	public Long dbSize() {
		Number num = store.size();
		return num.longValue();
	}

	/**
	 * 获取符合匹配规则的键
	 * @param pattern
	 * @return
	 */
	@Override
	public Set<String> keys(String pattern) {
		Set<String> result = new HashSet<String>();
		for(Object key:store.keySet()){
			if(key instanceof String){
				if(((String)key).startsWith(pattern)){
					result.add((String)key);
				}				
			}
		}
		return result;		
	}

	@Override
	public Long rpush(String key, String... strings) {		 
		return rpush(key, expire, strings);
	}
		
	/**
	 * 往列表中添加值
	 * @param key
	 * @param expire 有效时间
	 * @param strings
	 * @return
	 */
	@Override
	public Long rpush(String key, int expire, String... strings) {
		if(!store.containsKey(key)){
			store.put(key, new ArrayList<String>());
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>)store.get(key);
		for(String v:strings){
			list.add(v);
		}
		return null;
	}
	
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
	public Long lpush(String key, int expire, String... strings) {
		if(!store.containsKey(key)){
			store.put(key, new ArrayList<String>());
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>)store.get(key);
		for(String v:strings){			
			list.add(0,v);
		}
		return null;
	}
	
	/**
	 * 取出并获取列表中的最后一个元素
	 * @param key
	 * @return
	 */
	@Override
	public String rpop(String key){
		if(!store.containsKey(key)){
			store.put(key, new ArrayList<String>());
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>)store.get(key);
		String result = null;
		if(list.size()>0){
			result = list.get(list.size()-1);
			list.remove(list.size()-1);
		}
		return result;
	}
	
	/**
	 * 获取并取出列表中的第一个元素
	 * @param key
	 * @return
	 */
	@Override
	public String lpop(String key){
		if(!store.containsKey(key)){
			store.put(key, new ArrayList<String>());
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>)store.get(key);
		String result = null;
		if(list.size()>0){
			result = list.get(0);
			list.remove(0);
		}
		return result;
	}

	@Override
	public Long llen(String key) {
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>)store.get(key);
		Long result = 0L;
		if(list!=null){
			result = (long)list.size();
		}
		return result;
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>)store.get(key);
		if(list!=null){			
			int tmpEnd = longToInt(end);
			if(tmpEnd>list.size()){
				tmpEnd = list.size();
			}
			return list.subList(longToInt(start), tmpEnd);
		}
		return null;
	}

	@Override
	public Long lrem(String key, long count, String value) { 
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>)store.get(key);
		if(list!=null){			
			list.remove(value);
		}
		return null;
	}

	@Override
	public String ltrim(String key, long start, long end) {
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>)store.get(key);
		if(list!=null){			
			int tmpEnd = longToInt(end);
			if(tmpEnd>list.size()){
				tmpEnd = list.size();
			}
			List<String> subList = list.subList(longToInt(start), tmpEnd);
			store.put(key, subList);
		}
		return null;
	}

	@Override
	public long hset(String key, String field, String value) {
		return hset(key, this.expire, field, value);
	}

	@Override
	public long hset(String key, int seconds, String field, String value) {
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>)store.get(key);		
		if(map==null){
			map = new HashMap<String,String>();
			store.put(key, map);			
		}
		map.put(field, value);
		return 1;
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
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>)store.get(key);		
		if(map==null){
			map = new HashMap<String,String>();
			store.put(key, map);			
		}
		for(int i=0;i<fields.length;i++){				
			map.put(fields[i], values[i]);
		}		 	
		return result;
	}

	@Override
	public String hget(String key, String field) {
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>)store.get(key);
		if(map!=null){
			return map.get(field);
		}
		return null;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>)store.get(key);
		return map;
	}	
	
	/**
	 * 删除哈希表指定的field
	 * @param key
	 * @param field
	 * @return
	 */
	@Override
	public long hdel(String key, String... fields) {
		long result = 0;
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>)store.get(key);
		if(fields!=null){
			for(String field:fields){
				map.remove(field);
				result++;
			}
		}
		return result;
	}	
	
	private int longToInt(long l){
		int result = (int)l;
		if(l>=Integer.MAX_VALUE){
			result = Integer.MAX_VALUE;
		}
		return result;
	}

	@Override
	public long expire(String key, int seconds) {
		return seconds;
	}	
	
	@Override
	public long expire(byte[] key, int seconds) {
		return seconds;
	}

	@Override
	public long sadd(String key, String... members) {		 
		return sadd(key,this.expire,members);
	}

	@Override
	public long sadd(String key, int seconds, String... members) {
		@SuppressWarnings("unchecked")
		Set<String> set = (Set<String>)store.get(key);		
		if(set==null){
			set = new HashSet<String>();
			store.put(key, set);			
		}	
		long result = 0;
		for(String member:members){
			set.add(member);
			result++;
		}
		return result;
	}

	@Override
	public long scard(String key) {
		@SuppressWarnings("unchecked")
		Set<String> set = (Set<String>)store.get(key);
		long result = 0;
		if(set!=null){
			result = set.size();
		}			
		return result;
	}

	@Override
	public boolean sismember(String key, String member) {
		@SuppressWarnings("unchecked")
		Set<String> set = (Set<String>)store.get(key);
		boolean result = false;
		if(set!=null){
			result = set.contains(member);
		}			
		return result;
	}

	@Override
	public Set<String> smembers(String key) {
		@SuppressWarnings("unchecked")
		Set<String> set = (Set<String>)store.get(key);
		return set;
	}

	@Override
	public long srem(String key, String... members) {
		@SuppressWarnings("unchecked")
		Set<String> set = (Set<String>)store.get(key);		
		if(set==null){
			set = new HashSet<String>();
			store.put(key, set);			
		}	
		long result = 0;		
		for(String member:members){
			if(set.remove(member)){
				result++;	
			}			
		}
		return result;
	}
	
	
}
