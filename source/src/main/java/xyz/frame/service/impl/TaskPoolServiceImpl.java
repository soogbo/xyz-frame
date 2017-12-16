package xyz.frame.service.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import xyz.frame.service.TaskPoolService;

/**
 * @Description
 * @author shisp
 * @date 2017年11月28日 下午8:12:41
 */
@Service("taskPoolService")
public class TaskPoolServiceImpl implements TaskPoolService {

	private static final Logger logger = LoggerFactory.getLogger(TaskPoolServiceImpl.class);

	@Resource(name = "commonTaskExecutor")
	private TaskExecutor taskExecutor;

	@Override
	public void testTaskPool() {
		for (int i = 0; i < 10; i++) {
			logger.info("for i={}", i);
			
			//方式1：Runnable接口的匿名内部类
			taskExecutor.execute(new Runnable() {
			    @Override
			    public void run() {
			        Thread currentThread = Thread.currentThread();
			        logger.info("Runnable：now thread name={}", currentThread.getName());
			    }
			});
			//方式2：java8 lambda表达式
			taskExecutor.execute(()->{
			        Thread currentThread = Thread.currentThread();
			        logger.info("lambda ：now thread name={}", currentThread.getName());
			});
			
			//方式3 ：执行Future任务获取异步计算结果
			FutureTask<Long> futureTask = new FutureTask<>(new Callable<Long>() {
				@Override
				public Long call() throws Exception {
					System.out.println("run futureTask !!!");
					return 520L;
				}
			});
			
			taskExecutor.execute(futureTask);
			
			try {
				Long long1 = futureTask.get();
				System.out.println("get futureTask result :"+long1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
			
		}

		
		
        /*
         * taskExecutor.execute(new FutureTask<>(() -> { return null; }));
         */

        /*
         * future = taskExecutor.execute(() -> { logger.info("for i={}", t);
         * boolean flag = true; return flag; }); futureList.add(future);
         */

	}
}
