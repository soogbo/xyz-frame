package xyz.java.main.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * 锁
 * 
 * @author shisp
 * @date 2018-12-19 13:09:46
 */
public class LockDemo {

    public static void main(String[] args) throws InterruptedException {
        readWriteLock();
        reentrantLock();
        synchronizedMethod(); 
    }

    /**
     * 读写锁：写锁只能被一个线程持有，没有线程持有写锁时，读锁可以被多个线程持有进行读取，提高读取性能
     * readWriteLock接口，实现：ReentrantReadWriteLock
     */
    private static void readWriteLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        
        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                TimeUnit.SECONDS.sleep(3);
                map.put("write", "写锁写入的内容");
                System.out.println("写锁持有，写入，此时读锁要等写锁释放后才能获取！");
            } catch (InterruptedException e) {
            } finally {
                lock.writeLock().unlock();
                System.out.println("写锁释放！");
            }
        });
        
        Runnable readTask = () -> {
            System.out.println("读锁等待写锁结束释放");
            lock.readLock().lock();
            try {
                System.out.println("写锁释放后，读锁被多个线程同时持有，同时执行：" + map.get("write"));
            } finally {
                lock.readLock().unlock();
            }
        };
        
        executor.submit(readTask);
        executor.submit(readTask);
    }

    /**
     * 可重入锁,（互斥锁）
     */
    private static void reentrantLock() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // 同步执行：synchronized
        Casenum casenum3 = new Casenum();
        IntStream.range(0, 10000).forEach(i -> executor.submit(()->casenum3.addReentrantLock()));
        TimeUnit.SECONDS.sleep(3);
        System.out.println("reentrantLock同步执行：结果为10000：" + casenum3.count);
        
        int num = 0;
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            num++;
        } finally {
            lock.unlock();
        }
        System.out.println(num);
        System.out.println("Locked: " + lock.isLocked());
        System.out.println("Held by me: " + lock.isHeldByCurrentThread());
        boolean locked = lock.tryLock();
        System.out.println("Lock acquired: " + locked);
        
    }

    /**
     * synchronized同步方法，
     */
    private static void synchronizedMethod() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // 异步执行，出现脏读
        Casenum casenum = new Casenum();
        IntStream.range(0, 10000).forEach(i -> executor.submit(()->casenum.add()));
        TimeUnit.SECONDS.sleep(3);
        System.out.println("同步执行：结果<10000：出现脏读：" + casenum.count); 
        
        // 同步执行：ReentrantLock
        Casenum casenum2 = new Casenum();
        IntStream.range(0, 10000).forEach(i -> executor.submit(()->casenum2.addSync()));
        TimeUnit.SECONDS.sleep(3);
        System.out.println("synchronized同步执行：结果为10000：" + casenum2.count);
    }
}

class Casenum {
    ReentrantLock lock = new ReentrantLock();

    int count = 0;

    void add() {
        count++;
    }
    
    synchronized void addSync() {
        count++;
    }
    
    void addReentrantLock() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

}
