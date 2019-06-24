package xyz.frame.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态缓存
 * @author marshal.liu
 */
public class StaticCache {	
	private StaticCache(){		
	}
	private static StaticCache instance = new StaticCache();
	public static StaticCache getInstance(){
		return instance;
	}
	private Map<String,Object> cacheMap = new HashMap<String,Object>();
	
	/**
	 * 缓存一个对象
	 * @param key 键
	 * @param value 被缓存的内容对象
	 */
	public void put(String key,Object value){
		cacheMap.put(key, value);
	}
	
	/**
	 * 根据键获取缓存对象
	 * @param key 键
	 * @return
	 */
	public Object get(String key){
		return cacheMap.get(key);
	}
	
}
