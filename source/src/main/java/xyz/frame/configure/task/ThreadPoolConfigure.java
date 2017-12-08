package xyz.frame.configure.task;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 * @author marshal.liu
 */
@Configuration
public class ThreadPoolConfigure {

	/**
	 * 公用线程池
	 * @return ThreadPoolTaskExecutor
	 */
	@Bean(name="commonTaskExecutor")
	@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ThreadPoolTaskExecutor getUpdateAmountTaskExecutor(){
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(10); // 最少线程数2	
		pool.setMaxPoolSize(15); // 最大线程数
		pool.setQueueCapacity(1000); // 任务队列数量
		pool.setKeepAliveSeconds(60);
		//线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        //<!-- AbortPolicy:直接抛出java.util.concurrent.RejectedExecutionException异常 -->
        //<!-- CallerRunsPolicy:主线程直接执行该任务，执行完之后尝试添加下一个任务到线程池中，可以有效降低向线程池内添加任务的速度 -->
        //<!-- DiscardOldestPolicy:抛弃旧的任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->
        //<!-- DiscardPolicy:抛弃当前任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->
		pool.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());		
		pool.setAwaitTerminationSeconds(10);// 线程空间结束的时间
		pool.setWaitForTasksToCompleteOnShutdown(true); // 等待线程结束再退出
		pool.afterPropertiesSet(); // 初始化
		return pool;
	}
	
	/**
	 * 导入和操作公用的线程池 
	 * @return ThreadPoolTaskExecutor
	 */
	@Bean(name="importTaskExecutor")
	@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ThreadPoolTaskExecutor getImportTaskExecutor(){
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(20); // 最少线程数2	
		pool.setMaxPoolSize(100); // 最大线程数
		pool.setQueueCapacity(4000); // 任务队列数量
		pool.setKeepAliveSeconds(60);
		pool.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());		
		pool.setAwaitTerminationSeconds(10);// 线程空间结束的时间
		pool.setWaitForTasksToCompleteOnShutdown(true); // 等待线程结束再退出
		pool.afterPropertiesSet(); // 初始化
		return pool;
	}
	
	/**
	 * 短信功能的线程池
	 * @return ThreadPoolTaskExecutor
	 */
	@Bean(name="smsManagerExecutor")
	@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ThreadPoolTaskExecutor getSmsManagerExecutor(){
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(10); // 最少线程数2	
		pool.setMaxPoolSize(100); // 最大线程数
		pool.setQueueCapacity(2000); // 任务队列数量
		pool.setKeepAliveSeconds(60);
		pool.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());		
		pool.setAwaitTerminationSeconds(10);// 线程空间结束的时间
		pool.setWaitForTasksToCompleteOnShutdown(true); // 等待线程结束再退出
		pool.afterPropertiesSet(); // 初始化
		return pool;
	}
}
