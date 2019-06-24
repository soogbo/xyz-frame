package xyz.frame.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单机缓存工具, 默认三小时有效
 *
 * @author shisp
 */
public class CacheUtils {
	public static final Logger logger = LoggerFactory.getLogger(CacheUtils.class);

	private CacheUtils(){
	}

	/**
	 * 内存中保存当前服务器的taskId，mq接收状态后，找到taskId才消费:key=taskId,value=serviceNum
	 */
	private static final ConcurrentMap<String, CacheObj> TASK_ID_MAP = new ConcurrentHashMap<>(2048);
	/**
	 * 默认过期时间，3小时
	 */
	private static final Integer EXPIRE_HOUR = 3;
	/**
	 * 默认清理时间，1小时
	 */
	private static final Integer CLEAN_HOUR = 1;
	/**
	 * 清理线程
	 */
	private static ExecutorService executor = Executors.newSingleThreadExecutor();
	
	/**
	 * 清理线程开关
	 */
	private static Boolean cleanThreadSwitch = true;
	
	/**
	 * 开启清理过期缓存的线程
	 */
	static {
		logger.info("clean taskId cache thread run");
		if (cleanThreadSwitch) {
			executor.submit(CacheUtils::cleanTimeOutThread);
		}
	}
	
	/**
	 * 清理线程
	 */
	private static void cleanTimeOutThread() {
		while (cleanThreadSwitch) {
			logger.info("clean taskId cache thread run");
			CacheUtils.deleteTimeOut();
			try {
				TimeUnit.HOURS.sleep(CacheUtils.EXPIRE_HOUR);
				//TimeUnit.SECONDS.sleep(10); // 1测试10s清理一次

				Thread.sleep(CacheUtils.CLEAN_HOUR);
			} catch (Exception e) {
				logger.info("clean taskId cache thread run，Exception，", e);
			}
		}
	}
	/**
	 * 删除过期的缓存
	 */
	private static void deleteTimeOut() {
		logger.info("delete time out run!");
		List<String> deleteTaskList = new ArrayList<>();
		long currentTimeMillis = System.currentTimeMillis();
		for (Map.Entry<String, CacheObj> entry : TASK_ID_MAP.entrySet()) {
			if (entry.getValue().getExpirTime() < currentTimeMillis) {
				deleteTaskList.add(entry.getKey());
			}
		}
		for (String deleteTaskId : deleteTaskList) {
			removeTaskId(deleteTaskId);
		}
		logger.info("delete cache count is :{}", deleteTaskList.size());
		logger.info("cache map size={}", TASK_ID_MAP.size());
	}
	
	
	/**
	 * 获取过期时间
	 */
	public static long getExpirTime() {
		 return DateUtil.getHourByOffset(new Date(), EXPIRE_HOUR);
	}
	
	public static CacheObj put(String taskId, String serviceNum) {
		return TASK_ID_MAP.put(taskId, CacheObj.create(serviceNum));
	}
	
	public static boolean containsKey(String taskId) {
		return StringUtils.isNotBlank(taskId) && TASK_ID_MAP.containsKey(taskId);
	}
	
	public static CacheObj getTaskValue(String taskId) {
		return TASK_ID_MAP.get(taskId);
	}
	
	public static CacheObj removeTaskId(String taskId) {
		return TASK_ID_MAP.remove(taskId);
	}
	
	public static class CacheObj {
		private String serviceNum;
		private long expirTime;
		
		public static CacheObj create(String serviceNum) {
			return new CacheObj(serviceNum, DateUtil.getDayByOffsetLong(new Date(), EXPIRE_HOUR));
			//return new CacheObj(serviceNum, DateUtil.getSecondByOffset(new Date(), 30)); //2测试过期时间为s
		}
		
		public CacheObj() {
		}
		private CacheObj(String serviceNum, long expirTime) {
			super();
			this.serviceNum = serviceNum;
			this.expirTime = expirTime;
		}

		public String getServiceNum() {
			return serviceNum;
		}
		public void setServiceNum(String serviceNum) {
			this.serviceNum = serviceNum;
		}
		public long getExpirTime() {
			return expirTime;
		}
		public void setExpirTime(long expirTime) {
			this.expirTime = expirTime;
		}
	}
	
	public static void main(String[] args) {
		// 测试demo，修改位置两处
		// 1测试10s清理一次
		// 2测试过期时间为s
		for (int i = 0; i < 2000; i++) {
			put("aa:"+i, "bb:"+i);
			logger.info("cache map size={}", TASK_ID_MAP.size());
			if (i==600) {
				try {
					TimeUnit.SECONDS.sleep(20);
				} catch (Exception e) {
					logger.info("cache map Exception");
				} 
			}
		}
		try {
			TimeUnit.HOURS.sleep(20);
		} catch (Exception e) {
			logger.info("cache map Exception");
		} 
	}
}