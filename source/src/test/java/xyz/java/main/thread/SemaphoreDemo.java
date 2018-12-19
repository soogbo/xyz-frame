package xyz.java.main.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 信号量：定义信号量数，限制线程并发访问的个数：同一时间
 * 
 * @author shisp
 * @date 2018-12-19 14:01:05
 */
public class SemaphoreDemo {
    
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(3);
        
        // 模拟同时只能有三个线程执行
        Runnable runnable = ()->{
            boolean acquire = false;
            try {
                acquire = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                
                if (acquire) {
                    System.out.println("获取到信号，执行");
                }else {
                    System.out.println("没有获取到信号，不执行");
                }
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }finally {
                if (acquire) {
                    semaphore.release();
                }
            }
        };
        
        IntStream.range(0, 10).forEach(i -> {
            executor.submit(runnable);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        });
        
        /*
         *
         *                1获取到信号，执行
         *                2获取到信号，执行
         *                3没有获取到信号，不执行
         *                4没有获取到信号，不执行
         *                5获取到信号，执行
         *                6获取到信号，执行
         *                7获取到信号，执行
         *                8没有获取到信号，不执行
         *                9没有获取到信号，不执行
         * 
         * 
         */
        
        TimeUnit.SECONDS.sleep(10);
    }
}
