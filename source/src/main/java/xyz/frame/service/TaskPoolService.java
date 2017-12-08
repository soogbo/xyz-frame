/**
 * 
 */
package xyz.frame.service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @Description 测试线程池
 * @author shisp
 * @date 2017年12月8日  上午11:58:23
 */
public interface TaskPoolService {

    List<Future<?>> testTaskPool();
    
}
