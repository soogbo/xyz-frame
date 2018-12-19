package xyz.java.main.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 线程与线程池
 * 
 * @author shisp
 * @date 2018-12-18 11:00:15
 */
public class TheadDemo {

    public static void main(String[] args) {
        
        extendsThread();
        implRunnable();
        executorService();
        callableInvokeAll();
        callableInvokeAny();
        closeExecutor();
        createExecutorSorts();
        scheduledExecutorService();
        
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程池关闭
     */
    private static void closeExecutor() {
        ExecutorService executor = Executors.newWorkStealingPool();
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
            }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

    /**
     * 继承Thread
     */
    private static void extendsThread() {
        Thread t1 = new MyThread();
        t1.setName("MyThread");
        // start方法异步执行，t1线程调用
        t1.start();
        System.out.println("TheadDemo1:" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TheadDemo2:" + Thread.currentThread().getName());
        // run方法同步执行，main线程调用
        t1.run();
    }
    
    /**
     * 实现Runnable
     */
    private static void implRunnable() {
        Runnable t2 = new MyThreadRunnable();
        t2.run();
        Thread t1 = new Thread(t2);
        t1.setName("t1");
        t1.run();
        t1.start();
        
        //lambda
        Runnable t3 = ()-> System.out.println("t3:" + Thread.currentThread().getName());
        new Thread(t3).start();
        
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 线程池
     */
    private static void executorService() {
        Runnable t4 = new MyThreadRunnable();
        
        // 单线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(t4);
        Future<Integer> future = executorService.submit(()-> {
            System.out.println("call");   
          try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          System.out.println("call");
          return 8;
        });
        
        
        Integer integer = 0;
        try {
            integer = future.get(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        } catch (TimeoutException e) {
            System.out.println("TimeoutException");
        }
        System.out.println(integer);
        
    }
    
    /**
     * 创建线程池种类
     */
    private static void createExecutorSorts() {
        // 单线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 固定线程数线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        
        // 返回一个ForkJoinPool类型的 executor
        // ForkJoinPools使用一个并行因子数来创建，默认值为主机CPU的可用核心数
        ExecutorService newWorkStealingPool = Executors.newWorkStealingPool();
        
        // 调度线程池，可延迟执行
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    }
    
    /**
     * executor.invokeAll：一次执行多个Callable
     */
    private static void callableInvokeAll() {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");

        try {
            executor.invokeAll(callables).stream().map(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * executor.invokeAny：会阻塞，只到第一个future返回,只获得第一个future结果,其他线程后台继续执行完成
     */
    private static void callableInvokeAny() {
        
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("run task1");
                    return "task1";
                },
                () -> {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("run task2");
                    return "task2";
                },
                () -> {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("run task3");
                    return "task3";
                });

        String result = "";
        try {
            result = executor.invokeAny(callables);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        System.out.println(result);
    }
    
    /**
     * 调度线程池
     */
    private static void scheduledExecutorService() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);
        
        try {
            // future.getDelay获取剩余的延迟时间
            long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
            
            System.out.println("剩余的延迟时间" + remainingDelay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int initialDelay = 0; //初始化延迟(第一次执行时的延迟)
        
        int frequency = 1; //执行频率
        // scheduleAtFixedRate：固定频率执行，接收一个初始化延迟(第一次执行时的延迟)，不会等上一次结束
        executor.scheduleAtFixedRate(task, initialDelay, frequency, TimeUnit.SECONDS);
        
        int interval = 1; //执行间隔，上次结束后一段时间开始下一次执行
        // scheduleWithFixedDelay：固定间隔执行，接收一个初始化延迟(第一次执行时的延迟)
        executor.scheduleWithFixedDelay(task, initialDelay, interval, TimeUnit.SECONDS);
    }
    
}
    
    
/**
 * 自定义线程：继承Thread
 */
class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("MyThread:" + Thread.currentThread().getName());
    }
}
/**
 * 自定义线程：实现Runnable
 */
class MyThreadRunnable implements Runnable {
    
    @Override
    public void run() {
        System.out.println("MyThreadRunnable:" + Thread.currentThread().getName());
    }
}
