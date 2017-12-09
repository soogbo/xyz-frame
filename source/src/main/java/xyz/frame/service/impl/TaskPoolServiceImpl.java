package xyz.frame.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
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
 * @param <V>
 * @date 2017年11月28日 下午8:12:41
 */
@Service("taskPoolService")
public class TaskPoolServiceImpl<V> implements TaskPoolService {

	private static final Logger logger = LoggerFactory.getLogger(TaskPoolServiceImpl.class);

	@Resource(name = "commonTaskExecutor")
	private TaskExecutor taskExecutor;
//	private FutureTask taskExecutor2;

	@Override
	public List<Future<?>> testTaskPool() {
		List<Future<?>> futureList = new ArrayList<>();
//		Future<?> future = null;
		for (int i = 0; i < 10; i++) {
			logger.info("for i={}", i);
//			final int t = i;

			taskExecutor.execute(new FutureTask<>(() -> {
				return null;
			}));

			/*
			 * future = taskExecutor.execute(() -> { logger.info("for i={}", t);
			 * boolean flag = true; return flag; }); futureList.add(future);
			 */

			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {

				}
			});

		}
		return futureList;

	}
}
