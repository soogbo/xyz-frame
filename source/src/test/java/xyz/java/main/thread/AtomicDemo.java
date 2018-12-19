package xyz.java.main.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 原子类，用于执行原子操作:严重依赖于CAS，并发修改单个变量时可以使用
 * 
 * @author shisp
 * @date 2018-12-19 19:39:56
 */
public class AtomicDemo {

    public static void main(String[] args) throws InterruptedException {
        
        AtomicInteger();
    }
    
    /**
     * 原子类
     * @throws InterruptedException 
     */
    static void AtomicInteger() throws InterruptedException{
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger ai = new AtomicInteger(0);
        IntStream.range(0, 100).forEach(i->executor.submit(ai::incrementAndGet));
        TimeUnit.SECONDS.sleep(5);
        System.out.println("多线程并发操作原子类，同步执行方法：ai应为100：结果为：" + ai.get());
        
        AtomicInteger at = new AtomicInteger(0);
        int incrementAndGet = at.incrementAndGet();
        System.out.println("incrementAndGet()：自增1，并返回结果：" + incrementAndGet);
        
        int updateAndGet = at.updateAndGet(n-> n+2);
        System.out.println("updateAndGet(，，)：可接收lambda表达式，并返回结果：" + updateAndGet);
    }
}
