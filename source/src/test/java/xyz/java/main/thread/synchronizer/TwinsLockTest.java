package xyz.java.main.thread.synchronizer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 测试TwinsLock
 * 
 * @author shisp
 * @date 2018-12-25 16:13:43
 */
public class TwinsLockTest {
    
    public static void main(String[] args) {
        testTwinsLock();
    }

    public static void testTwinsLock() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}