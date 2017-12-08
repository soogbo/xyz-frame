package xyz.frame.configure.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 提交异步执行的方法
 */
@Deprecated
public interface TaskExecutor {

    /**
     * 返回具体执行的业务异常
     *
     * @param e
     * @return
     */
    static Throwable getExecuteException(ExecutionException e) {
        if (e == null) {
            return null;
        }

        if (e.getCause() instanceof ConcurrentException) {
            return e.getCause().getCause();
        } else {
            return e.getCause();
        }
    }

    /**
     * 异步执行service
     *
     * @param beanName
     * @param method
     * @param args
     * @return
     * @throws ConcurrentException
     */
    Future<?> execute(String beanName, String method, Object... args) throws ConcurrentException;
    
    /**
     * 异步执行
     * @param callable
     * @return
     * @throws ConcurrentException
     */
    Future<?> execute(Callable<?> callable)throws ConcurrentException;
    
    void execute(Runnable runnable)throws ConcurrentException;
}
